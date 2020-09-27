package com.papple.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.papple.blog.models.Hashtag;
import com.papple.blog.models.HashtagPK;
import com.papple.blog.models.Post;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long>{
	Hashtag save(Hashtag hatshtag);
	
	@Query(value = "select * from hashtag where postid = ?1", nativeQuery = true)
	List<Hashtag> findByPostid(Long Id);
	
	@Query(value = "select * from hashtag h where exists(select * from post p where h.postid = p.id and p.writer = ?1)", nativeQuery = true)
	List<Hashtag> myHashCategory(String email);	//내가 쓴 해시태그 리스트 출력(Category 목록)
	
	@Transactional
	@Modifying
	@Query(value = "delete h from hashtag h where exists(select * from post p where h.postid = p.id and p.writer = ?1)", nativeQuery = true)
	void deleteHashtagByEmail(String email);	//아이디 삭제시, 관련 해시태그 삭제
	
	@Transactional
	@Modifying
	@Query(value = "delete from hashtag where postid = ?1", nativeQuery = true)
	void deleteHashtagByPostid(Long postid);
}
