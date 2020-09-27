package com.papple.blog.security.services;

import java.util.List;

import com.papple.blog.models.Hashtag;
import com.papple.blog.models.HashtagPK;

public interface HashtagService {
	Hashtag save(Hashtag hatshtag);
	List<Hashtag> findByPostid(Long Id);
	List<Hashtag> myHashCategory(String email);
	void deleteHashtagByEmail(String email);
	void deleteHashtagByPostid(Long postid);
}
