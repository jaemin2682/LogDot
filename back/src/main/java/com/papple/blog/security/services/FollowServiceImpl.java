package com.papple.blog.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.papple.blog.models.Follow;
import com.papple.blog.models.FollowPK;
import com.papple.blog.repository.FollowRepository;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	private FollowRepository followRepository;
	
	@Override
	public List<Follow> findByMyEmail(String follower) {
		return followRepository.findByMyEmail(follower);
	}

	@Override
	public int MyFollowerCnt(String followed) {
		return followRepository.MyFollowerCnt(followed);
	}

	@Override
	public int isFollow(String follower, String followed) {
		return followRepository.isFollow(follower, followed);
	}

	@Override
	public void deleteFollow(String follower, String followed) {
		followRepository.deleteFollow(follower, followed);
		return;
	}
	@Override
	public void deleteByEmail(String email) {
		followRepository.deleteByEmail(email);
		return;
	}

	@Override
	public Follow save(Follow follow) {
		return followRepository.save(follow);
	}

	@Override
	public List<Follow> findByFollowed(String followed) {
		return followRepository.findByFollowed(followed);
	}
	
}
