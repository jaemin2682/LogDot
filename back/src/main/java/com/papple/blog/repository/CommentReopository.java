package com.papple.blog.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.papple.blog.models.Comment;
import com.papple.blog.payload.response.CommentResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReopository extends JpaRepository<Comment, Long> {

    @Modifying
	@Transactional
	@Query(value = "delete from comment where replyto = ?1", nativeQuery = true)
    void deleteByReplyto(Long replyto);
    
    @Modifying
	@Transactional
	@Query(value = "delete from comment where postid = ?1", nativeQuery = true)
    void deleteByPostid(Long postid);
}