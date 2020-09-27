package com.papple.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.papple.blog.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User getUserByEmail(String email);

	Boolean existsByNickname(String nickname);

	// 닉네임 등록 및 수정
	@Transactional
	@Modifying
	@Query("UPDATE User SET nickname = ?1 WHERE email = ?2")
	Integer updateNickname(String nickname, String email);

	// 비밀번호 재설정
	@Transactional
	@Modifying
	@Query("UPDATE User SET password = ?1 WHERE email = ?2")
	Integer resetPassword(String password, String email);

	// 회원가입 이메일 인증 
	@Transactional
	@Modifying
	@Query("UPDATE User SET userCertification = ?1 WHERE email = ?2")
	Integer updateAuth(Integer certification, String email);

	// 프로필 사진 업로드
	@Transactional
	@Modifying
	@Query(value = "update user set profile = ?1 where email = ?2", nativeQuery = true)
	Integer updateProfile(String profile, String email);
	
	// 프로필 사진 삭제
	@Transactional
	@Modifying
	@Query(value = "update user set profile = 'http://i3a604.p.ssafy.io/images/profile/basic.svg' where email = ?1", nativeQuery = true)
	void deleteProfile(String email);
}

