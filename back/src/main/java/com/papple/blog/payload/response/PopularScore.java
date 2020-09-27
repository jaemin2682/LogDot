package com.papple.blog.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PopularScore implements Comparable<PopularScore> {
	private Long postid;
	private Long score;
	public PopularScore() {
		this.postid = 0l;
		this.score = 0l;
	}

	@Override
	public int compareTo(PopularScore o) {
		return Long.compare(o.getScore(), this.getScore());
	}
}
