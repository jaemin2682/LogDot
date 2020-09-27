package com.papple.blog.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.papple.blog.models.Hashtag;
import com.papple.blog.models.Post;
import com.papple.blog.models.TemporaryPost;
import com.papple.blog.payload.response.PostList;

@Repository
public interface TempPostRepository extends JpaRepository<TemporaryPost, String>{

	@Query(value = "select count(*) from tempost where writer = ?1", nativeQuery = true)
	int isTemPost(String email);
}
