package com.papple.blog.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemPostResponse {
	private String writer;
	private String title;
	private String content;
	private List<String> tag;
	private String picture;
	private String summary;
	
	public TemPostResponse(String writer, String title, String content, String picture, String summary) {
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.picture = picture;
		this.summary = summary;
	}
}
