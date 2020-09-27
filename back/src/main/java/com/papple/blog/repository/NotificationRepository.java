package com.papple.blog.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.papple.blog.models.Notification;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

	List<Notification> findByTargetuserAndIsreadIsFalse(String email);		// 안읽은 알림
	List<Notification> findByTargetuserOrderByCreateatDesc(String email);	// 알림 모두 조회

	// 내 글 좋아요 (이전에 좋아요 눌렀었는지)
	Notification findByActionuserAndPostidAndType(String actionuser, Long postid, Integer type);
	// 내 댓글 좋아요 (이전에 좋아요 눌렀었는지)
	Notification findByActionuserAndCommentidAndType(String actionuser, Long commentid, Integer type);
	// 팔로우 알림
	Notification findByActionuserAndTargetuserAndType(String actionuser, String targetuser, Integer type);

	// 알림 표시
	@Transactional
	@Modifying
	@Query(value = "UPDATE notification SET isalert = true WHERE id = ?1", nativeQuery = true)
	void updateIsalertById(Long id);

	// 읽음 표시
	@Transactional
	@Modifying
	@Query(value = "UPDATE notification SET isread = true WHERE id = ?1", nativeQuery = true)
	void updateIsreadById(Long id);

	@Transactional
	@Modifying
	@Query(value = "TRUNCATE notification", nativeQuery = true)
	void deleteNotificationByCron();

	// 해당 사용자의 알림 모두 삭제
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM notification WHERE targetuser=?1", nativeQuery = true)
	void deleteByTargetuser(String email);

	// 해당 사용자의 읽은 알림 모두 삭제
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM notification WHERE targetuser=?1 and isread=true", nativeQuery = true)
	void deleteByTargetuserAndIsreadIsTrue(String email);
  
}