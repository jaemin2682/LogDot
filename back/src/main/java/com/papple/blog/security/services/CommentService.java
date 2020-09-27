package com.papple.blog.security.services;

import java.util.List;
import java.util.Optional;

import com.papple.blog.models.Comment;
import com.papple.blog.payload.response.CommentResponse;

public interface CommentService {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    List<CommentResponse> findByPostidAndReplytoIsNull(Long postid);
    List<CommentResponse> findByPostidAndReplyto(Long postid, Long replyto);
    void deleteById(Long id);
    void deleteByReplyto(Long id);
    void deleteByPostid(Long postid);
    void likeComment(String email, Long commentid);
    void unlikeComment(String email, Long commentid);
    int findByEmailAndCommentid(String email, Long commentid);
}