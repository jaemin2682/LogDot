package com.papple.blog.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.papple.blog.payload.response.FollowList;
import com.papple.blog.payload.response.FollowListNavi;

@Mapper
public interface ProfileRepository {
	List<String> searchByEmail(String email);
	int insertProfile(String email, String path);
	int updateProfile(String email, String path);
	int deleteProfile(String email, String path);
	int unProfile(String email);
	List<FollowList> myFollowList(String email);
	List<FollowListNavi> myFollowListNavi(String email);
	List<FollowList> searchUser(String word);
}
