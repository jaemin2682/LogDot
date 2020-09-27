package com.papple.blog.payload.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDetail {
	private String nickname;
	private String profile;
	private Long score;
	private Long id;
	private String title;
	private String content;
	private String summary;
	private String picture;
	private String writer;
	private int good;
	private int views;
    private String createdate;
    private List<String> tag;
    //좋아요 여부 - 비로그인 시 기본 false
    private boolean isgood;
    private boolean isstore;
    
    private String blogName;
	private String blogDescription;
	private String blogPicture;
	
	private int followerNum;
	private boolean isFollow;
    
    
    public PostDetail() {
		this.isgood = false;
		this.followerNum = 0;
		this.isFollow = false;
		this.isstore = false;
	}
}
