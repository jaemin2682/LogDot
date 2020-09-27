package com.papple.blog.repository;

import java.util.List;

import com.papple.blog.payload.response.CommentResponse;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentListRepository {
    List<CommentResponse> findByPostidAndReplytoIsNull(Long postid);
    List<CommentResponse> findByPostidAndReplyto(Long postid, Long replyto);

    void likeComment(String email, Long commentid);
    void unlikeComment(String email, Long commentid);

    int findByEmailAndCommentid(String email, Long commentid);
}