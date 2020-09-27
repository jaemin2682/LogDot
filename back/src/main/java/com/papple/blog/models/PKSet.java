package com.papple.blog.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PKSet implements Serializable{
    @NotBlank
    private String email;

    @NotBlank
    private Long postid;
}