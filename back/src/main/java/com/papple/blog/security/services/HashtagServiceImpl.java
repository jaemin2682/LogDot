package com.papple.blog.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.papple.blog.models.Hashtag;
import com.papple.blog.models.HashtagPK;
import com.papple.blog.repository.HashtagRepository;

@Service
public class HashtagServiceImpl implements HashtagService {

	@Autowired
	private HashtagRepository hashtagRepository;
	
	@Override
	public Hashtag save(Hashtag hashtag) {
		return hashtagRepository.save(hashtag);
	}

	@Override
	public List<Hashtag> findByPostid(Long Id) {
		return hashtagRepository.findByPostid(Id);
	}

	@Override
	public List<Hashtag> myHashCategory(String email) {
		return hashtagRepository.myHashCategory(email);
	}

	@Override
	public void deleteHashtagByEmail(String email) {
		hashtagRepository.deleteHashtagByEmail(email);
	}

	@Override
	public void deleteHashtagByPostid(Long postid) {
		hashtagRepository.deleteHashtagByPostid(postid);
	}

}
