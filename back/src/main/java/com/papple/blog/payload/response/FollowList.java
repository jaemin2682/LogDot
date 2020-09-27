package com.papple.blog.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowList {
	private String email;
	private String nickname;
	private String profile;
	private long score;
	private String blogname;
	private boolean isNotification;
	private boolean isFollow;
	
	public FollowList() {
		this.score = 0;
		this.isFollow = false;
		this.isNotification = true;	// 디폴트는 알람 ON
	}
}
