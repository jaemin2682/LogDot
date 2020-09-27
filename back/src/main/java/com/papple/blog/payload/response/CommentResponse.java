package com.papple.blog.payload.response;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CommentResponse {
    @NotBlank
    private Long id;
    private String email;                   // 댓글 작성자 이메일
    private Long postid;                    // 댓글 단 포스트 id
    private int likes;                      // 댓글 좋아요 수
    private String content;	                // 내용
    private Long replyto;	                // 대댓글일 경우, 부모 댓글의 id
    private LocalDateTime createdate;       // 댓글 쓴 날짜
    private int replycount;	                // 자식댓글이 있는가 없는가
    private int islike;                 // 좋아요를 눌렀는가
    private String nickname;                // 댓글 작성자 닉네임
    private String profile;                 // 댓글 작성자 프로필사진
    private Long score;                     // 댓글 작성자 점수
}