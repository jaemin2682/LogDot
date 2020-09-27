package com.papple.blog.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // 댓글 고유번호

    @NotBlank
    private String email;   // 댓글 작성자 이메일

    @NotNull
    private Long postid;    // 댓글 단 포스트 id

    private int likes;      // 댓글 좋아요 수
    
    @Size(max = 10000)
    private String content;	// 내용

    private Long replyto;	// 대댓글일 경우, 부모 댓글의 id

    @CreationTimestamp
    private LocalDateTime createdate;
    
    private int replycount;	//자식댓글이 있는가 없는가

    public Comment(){
        likes=0;
        replycount=0;
    }
    
}