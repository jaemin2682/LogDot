package com.papple.blog.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowListNavi {
	private String email;
	private String nickname;
	private String profile;
}
