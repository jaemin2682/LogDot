package com.papple.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.papple.blog.models.TagScore;

@Repository
public interface TagScoreRepository extends JpaRepository<TagScore, String>{
	
	@Query(value = "select count(*) from tagscore where tagname = ?1", nativeQuery = true)
	int isExist(String tagname);
	
	@Transactional
	@Modifying
	@Query(value = "update tagscore set score = score+1 where tagname = ?1", nativeQuery = true)
	void plusScore(String tagname);
	
	@Query(value = "select * from tagscore order by score desc", nativeQuery = true)
	List<TagScore> searchTagScore();
}
