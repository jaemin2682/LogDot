package com.papple.blog.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.papple.blog.models.Follow;
import com.papple.blog.models.FollowPK;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowPK>{
	
	@Query(value = "SELECT * FROM follow where followed = ?1", nativeQuery = true)
	List<Follow> findByFollowed(String followed); 

	@Query(value = "SELECT * FROM follow where follower = ?1", nativeQuery = true)
	List<Follow> findByMyEmail(String follower);
	
	@Query(value = "SELECT count(*) FROM follow where followed = ?1", nativeQuery = true)
	int MyFollowerCnt(String followed);
	
	@Query(value = "select count(*) from follow where follower = ?1 and followed = ?2", nativeQuery = true)
	int isFollow(String follower, String followed);

	@Modifying
	@Transactional
	@Query(value = "delete from follow where follower = ?1 and followed = ?2", nativeQuery = true)
	void deleteFollow(String follower, String followed);

	@Modifying
	@Transactional
	@Query(value = "delete from follow where follower = ?1 or followed = ?1", nativeQuery = true)
	void deleteByEmail(String email);	// 회원삭제시 팔로우 삭제

}
