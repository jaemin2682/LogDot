package com.papple.blog.security.services;

import java.util.List;

import com.papple.blog.models.Follow;
import com.papple.blog.models.FollowPK;

public interface FollowService {
	List<Follow> findByFollowed(String followed); 
	List<Follow> findByMyEmail(String follower);
	int MyFollowerCnt(String followed);
	int isFollow(String follower, String followed);
	void deleteFollow(String follower, String followed);
	void deleteByEmail(String email);
	Follow save(Follow follow);
}
