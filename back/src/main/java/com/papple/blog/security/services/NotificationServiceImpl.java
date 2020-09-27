package com.papple.blog.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.papple.blog.models.Notification;
import com.papple.blog.models.User;
import com.papple.blog.payload.response.StreamDataSet;
import com.papple.blog.repository.NotificationRepository;
import com.papple.blog.repository.UserRepository;

@Slf4j
@Service
@EnableScheduling
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;

    private ConcurrentHashMap<String, StreamDataSet> eventMap = new ConcurrentHashMap<>();
    
    public void addEmitter(String email, StreamDataSet dataSet) {
        eventMap.put(email, dataSet);
    }

    public void removeEmitter(String email) {
        eventMap.remove(email);
    }
    
    @Scheduled(initialDelay = 2000L, fixedDelay = 5000L)
    public void fetch() {

        if (eventMap.size() == 0) {
            return;
        }

        this.handleAlert();
    }

    @Transactional
    private void handleAlert() {
        List<String> toBeRemoved = new ArrayList<>(eventMap.size());
        List<Long> alertIdList = new ArrayList<>();

        for (Map.Entry<String, StreamDataSet> entry : eventMap.entrySet()) {

            final String email = entry.getKey();
            final StreamDataSet dataSet = entry.getValue();

            final User user = dataSet.getUser();
            final List<Notification> receivingAlert = notificationRepository.findByTargetuserAndIsreadIsFalse(user.getEmail());
            final int noneReadCount = receivingAlert.size();
            /** 접속 유저가 읽지 않은 알람의 개수 **/
            if (noneReadCount == 0) {
                continue;
            }

            final SseEmitter emitter = dataSet.getEmitter();

            /** 알림데이터 생성 **/
            /** ?분 이내에 작성된 알람 목록 확인 **/
            final List<Notification> alertList = getListAnMinuteAndAlertFalse(receivingAlert);

            if (alertList.size() == 0) {
                continue;
            }

            /** 알림 목록 ID 획득 **/
            alertIdList.addAll(alertList.stream()
                                    .map(Notification::getId)
                                    .collect(Collectors.toList()));

            try {
                /** 알림 전송 수행 **/
                emitter.send(alertList, MediaType.APPLICATION_JSON_UTF8);

            } catch (Exception e) {
                log.error("이미터 센드 시 에러 발생 :: {}", e.getMessage());
                toBeRemoved.add(email);
            }

        } // for

        /** 전송된 알람들 Isalert true 로 변경 **/
        updateIsAlert(alertIdList);

        /** 전송 오류 SseEmitter 제거 **/
        for (String uuid : toBeRemoved) {
            eventMap.remove(uuid);
        }
    }

    /**
     * - 하루 이전에 발생된 알람 여부
     * - 알람 푸시 수행 여부
     *
     * @param paramList 현재 접속 사용자에게 존재하는 전체 알림
     * @return 현재 시간으로부터 하루 이전에 발생한 알림 목록
     */
    private ArrayList<Notification> getListAnMinuteAndAlertFalse(List<Notification> paramList) {

        ArrayList<Notification> alertList = new ArrayList<>();

        // LocalDateTime beforeTime = LocalDateTime.now().minusMinutes(60*24);

        for (Notification notification : paramList) {
            
            // boolean isAlert = notification.isIsalert();
            // LocalDateTime createdAt = notification.getCreateat();
            // if (createdAt.isBefore(beforeTime) || isAlert) {
            //     continue;
            // }
            User user = userRepository.getUserByEmail(notification.getActionuser());
            notification.setProfile(user.getProfile());

            // 안 읽은 알리미
            alertList.add(notification);
        }

        return alertList;
    }
    /**
     * - 전송된 알림에 대해서 IS_Alert 값을 'true' 로 변경
     *
     * @param alertIds 전송된 알림 ID 목록
     */
    private void updateIsAlert(List<Long> alertIds) {

        Set<Long> idSet = new HashSet<>(alertIds);
        idSet.stream().forEach(notificationRepository::updateIsalertById);

    }
    // 알림 읽은 표시
    @Override
    public void updateIsreadById(Long id) {
        notificationRepository.updateIsreadById(id);
    }
    
    /**
     * reference :: https://crontab.guru/#0_2_*_*_*
     * 연단위 2021-01-01 에 알림 데이터는 삭제된다.
     *
     */
    @Scheduled(cron = "0 0 0 1 1 *")
    public void deleteNotificationByCron() {
        
        notificationRepository.deleteNotificationByCron();
        
    }

    // >>>>>>>>>>>> 부가기능
	@Override
	public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification findByActionuserAndPostidAndType(String actionuser, Long postid, Integer type) {
        return notificationRepository.findByActionuserAndPostidAndType(actionuser, postid, type);
    }

    @Override
    public Notification findByActionuserAndTargetuserAndType(String follower, String followed, Integer type) {
        return notificationRepository.findByActionuserAndTargetuserAndType(follower, followed, type);
    }

    @Override
    public void deleteByTargetuser(String email) {
        notificationRepository.deleteByTargetuser(email);
    }

    @Override
    public Notification findByActionuserAndCommentidAndType(String actionuser, Long commentid, Integer type) {
        return notificationRepository.findByActionuserAndCommentidAndType(actionuser, commentid, type);
    }

    @Override
    public List<Notification> findByTargetuser(String email) {
        return notificationRepository.findByTargetuserOrderByCreateatDesc(email);
    }

    @Override
    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public void deleteByTargetuserAndIsreadIsTrue(String email) {
        notificationRepository.deleteByTargetuserAndIsreadIsTrue(email);
    }

}