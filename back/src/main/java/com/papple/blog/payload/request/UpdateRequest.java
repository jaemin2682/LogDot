package com.papple.blog.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String newpassword;
}