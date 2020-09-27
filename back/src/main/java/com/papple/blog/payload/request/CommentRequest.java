package com.papple.blog.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentRequest {
    @NotNull
    private Long id;

    @NotBlank
    private String content;

}