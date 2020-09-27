package com.papple.blog.social;

import com.papple.blog.models.User;

import lombok.Getter;

@Getter
public class SessionUser {
    private String email, picture;
    
    public SessionUser(User user) {
        this.email = user.getEmail();
        this.picture = user.getProfile();
    }
}
