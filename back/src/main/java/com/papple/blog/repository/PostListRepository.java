package com.papple.blog.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.papple.blog.models.Post;
import com.papple.blog.payload.response.PostDetail;
import com.papple.blog.payload.response.PostList;

@Mapper
public interface PostListRepository{
	List<PostList> searchAllPost();
	List<PostList> searchByEmail(String email);
	PostList searchPostById(Long postid);

	List<PostList> searchByWord(String word);		// 단어 검색(title, content)
	List<PostList> searchByTag(String hashtag);		// 태그 검색
	
	List<PostList> searchPostByMyTag(String email, String hashtag);
	
	PostDetail searchPostDetail(Long postid);		// Post Detail 조회
	List<String> searchHashtag(Long postid);
	
	List<Long> findHistoryByUser(String email);						// 해당 사용자의 방문 기록 검색
	List<Long> findHistoryByUserPaging(String email, int page);		// 페이징
	
	List<Long> findGoodListByEmail(String email);			// 해당 사용자의 좋아요 리스트 검색
	List<Long> findGoodListByEmailPaging(String email, int page);		// 페이징
	
	List<Long> findStorageListByEmail(String email);		// 해당 사용자의 보관함 리스트 검색
	List<Long> findStorageListByEmailPaging(String email, int page);	// 페이징
	
	List<PostList> findLatestMyFollowPost(String email);		// 해당 사용자의 팔로워들의 최신 글 검색
	List<PostList> findLatestMyFollowPostPaging(String email, int page);	// 페이징
	List<PostList> findLatestMyFollowPostLimit10(String email);
	
	List<PostList> paging(int page);
	
}
