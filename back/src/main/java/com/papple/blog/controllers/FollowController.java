package com.papple.blog.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.papple.blog.models.Follow;
import com.papple.blog.models.FollowPK;
import com.papple.blog.models.Notification;
import com.papple.blog.models.User;
import com.papple.blog.payload.response.FollowList;
import com.papple.blog.payload.response.FollowListNavi;
import com.papple.blog.repository.NotificationRepository;
import com.papple.blog.repository.ProfileRepository;
import com.papple.blog.repository.UserRepository;
import com.papple.blog.repository.unNotificationRepository;
import com.papple.blog.security.services.FollowService;
import com.papple.blog.security.services.NotificationService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/follow")
public class FollowController {
	@Autowired
	private FollowService followService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private NotificationRepository NotificationRepository;

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private unNotificationRepository unnotificationRepository;

	@GetMapping("/list")
	@ApiOperation(value = "내가 팔로우한 사람들을 return (내가 팔로우 한 유저 리스트 - 팔로우 탭에서)")
	public ResponseEntity<List<FollowList>> searchFollowerByEmail(String email) throws Exception {
		List<FollowList> list = profileRepository.myFollowList(email);
		for(int i=0;i<list.size();i++){
			if(followService.isFollow(email, list.get(i).getEmail()) > 0) list.get(i).setFollow(true);	//팔로우 여부 업데이트
			// 알림 여부 ON(true) OFF(false)
			if(unnotificationRepository.findByTargetuserAndActionuser(email,list.get(i).getEmail()) != null) list.get(i).setNotification(false);
		}	
		//알람 여부 업데이트 해줘야함	
		return new ResponseEntity<List<FollowList>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/navilist")
	@ApiOperation(value = "내가 팔로우한 사람들을 return (내가 팔로우 한 유저 리스트 - 네비게이션 바에서)")
	public ResponseEntity<List<FollowListNavi>> searchFollowerByEmailInNavi(String email) throws Exception {
		return new ResponseEntity<List<FollowListNavi>>(profileRepository.myFollowListNavi(email), HttpStatus.OK);
	}
	
	@GetMapping("/cnt/{followed}")
	@ApiOperation(value = "나를 팔로워한 유저들의 수를 return (내 팔로워 수)")
	public ResponseEntity<Integer> MyFollowerCnt(@PathVariable String followed) throws Exception {
		System.out.println("나를 팔로우한 팔로워들의 수 return");
		return new ResponseEntity<Integer>(followService.MyFollowerCnt(followed), HttpStatus.OK);
	}
	
	@GetMapping("/isfollow")	//http://localhost:8081/api/follow/isfollow?follower=average10@naver.com&followed=average30@naver.com
	@ApiOperation(value = "팔로우 여부를 반환(1:팔로우중, 0:팔로우 아님)")
	public ResponseEntity<Integer> isFollow(String follower, String followed) throws Exception {
		System.out.println("팔로워 여부 return");
		return new ResponseEntity<Integer>(followService.isFollow(follower, followed), HttpStatus.OK);
	}
	
	@PostMapping("/add")	//http://localhost:8081/api/follow/add?follower=average10@naver.com&followed=average100@naver.com
	@ApiOperation(value = "팔로우 추가 - 내가 follower, 상대가 followed (성공시 success 반환, 실패시 fail 반환)")
	public ResponseEntity<String> addFollow(String follower, String followed) throws Exception {
		System.out.println("이 사람을 팔로우");

		Follow follow = new Follow(new FollowPK(follower, followed));
		followService.save(follow);

		// 알림발생(010000)
		String actionName = userRepository.getUserByEmail(follower).getNickname();
		User user = userRepository.getUserByEmail(followed);
        int setting = Integer.parseInt(user.getNotification(),2);
        // 알림 ON 했는지 && targetuser가 내 알림 껐는지
		if( (setting& (1<<4)) != 0
				&& unnotificationRepository.findByTargetuserAndActionuser(followed, follower) == null){
			Notification notification = Notification.builder()
				.message(actionName +"님이 당신을 팔로우 합니다.")
				.actionuser(follower)
				.targetuser(followed)
				.build();

				notification.setType(1<<4);
				notificationService.save(notification);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@DeleteMapping("/del") // http://localhost:8081/api/follow/del?follower=average10@naver.com&followed=average100@naver.com
	@ApiOperation(value = "팔로우 취소 - 내가 follower, 상대가 followed (그냥 success 반환.)")
	public ResponseEntity<String> delFollow(String follower, String followed) throws Exception {
		System.out.println("팔로우 취소");
		followService.deleteFollow(follower, followed);

		// 팔로우 알림 삭제
		Notification notification = NotificationRepository.findByActionuserAndTargetuserAndType(follower, followed, 16);

		if(notification != null){
			NotificationRepository.deleteById(notification.getId());
		}

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	
}

