package com.papple.blog.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class LikeCommentPK implements Serializable{
    @NotBlank
    private String email;

    @NotBlank
    private Long commentid;
}