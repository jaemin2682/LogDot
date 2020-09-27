package com.papple.blog.payload.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PostList {
		
	private Long id;
	private String title;
	private String summary;
	private String picture;
	private String writer;
	private int good;
	private int views;
	private String createdate;
	
	private String nickname;
	private String profile;
	private Long score;
    //좋아요 여부 - 비로그인 시 기본 false
    private boolean isgood;
	public PostList() {
		this.isgood = false;
	}
    
}
