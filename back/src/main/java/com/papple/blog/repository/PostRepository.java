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
import com.papple.blog.payload.response.PostList;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	List<Post> findAll();	//전체 글 검색
	List<Post> findByWriter(String writer);	//해당 작성자가 쓴 글 모두 검색
	Optional<Post> findById(Long id);	// 게시글 번호로 해당 게시글 반환
	Post save(Post post);	//새 글 쓰기
	void deleteById(Long id);
	
	// 작성자로 post 삭제
	@Transactional
	@Modifying
	@Query("DELETE FROM Post WHERE writer = ?1")
	void deleteByWriter(String email);

//	@Query(value = "select * from post p where exists(select * from hashtag h where h.postid = p.id and h.hashtag=?1) and p.writer = ?2", nativeQuery = true)
//	List<Post> findMyHashPost(String hashtag, String email);	// 내가 쓴 특정 해시태그의 글들을 출력(HashTag Category 안 게시물들) 

	@Query(value = "SELECT * FROM post WHERE post.writer IN (SELECT followed FROM follow WHERE follower = ?1) ORDER BY post.createdate"	, nativeQuery = true)
	List<Post> findFollowLatestByUser(String email);	// 팔로우한 사용자들의 최신 글 검색
	
	@Query(value = "SELECT * FROM post order by views + good * 2 desc", nativeQuery = true)
	List<Post> searchPopularPost();	// 전체 인기글 검색

	@Query(value = "SELECT * FROM post WHERE post.writer IN (SELECT followed FROM follow WHERE follower = ?1) order by views + good * 2 desc" , nativeQuery = true)
	List<Post> findFollowPopularByUser(String email);	// 팔로우한 사용자들의 인기 글 검색
	
	@Transactional
	@Modifying
	@Query(value = "update post set picture = ?1 where id = ?2", nativeQuery = true)
	void updatePicture(String picture, Long id);
	
	@Transactional
	@Modifying
	@Query(value = "update post set picture = null where id = ?1", nativeQuery = true)
	void deletePicture(Long id);
	
	@Query(value = "select count(*) from post p where writer=?1 and exists(select * from hashtag h where h.postid = p.id and h.hashtag = ?2)", nativeQuery = true)
	int cntCategory(String email, String hashtag);
	
	@Query(value = "select count(*) from post where writer = ?1", nativeQuery = true)
	int cntMyPost(String email);

}
