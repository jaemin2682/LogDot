package com.papple.blog.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String email;
	private String nickname;
	private List<String> roles;

	public JwtResponse(String accessToken, String email, String nickname, List<String> roles) {
		this.token = accessToken;
		this.email = email;
		this.nickname = nickname;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<String> getRoles() {
		return roles;
	}
}
