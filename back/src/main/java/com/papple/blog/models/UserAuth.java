package com.papple.blog.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table( name = "userauth")
public class UserAuth {
    @Id
    private String email;
    
    @NotBlank
    private String authkey;

    public UserAuth(){}

    public UserAuth(String email, String authkey){
        this.email = email;
        this.authkey = authkey;
    }
}