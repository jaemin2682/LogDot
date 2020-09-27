package com.papple.blog.security.services;

import java.util.List;
import java.util.Optional;

import com.papple.blog.models.Comment;
import com.papple.blog.payload.response.CommentResponse;
import com.papple.blog.repository.CommentListRepository;
import com.papple.blog.repository.CommentReopository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentReopository commentRopository;
    @Autowired
    private CommentListRepository commentListRopository;

    @Override
    public Comment save(Comment comment) {
        return commentRopository.save(comment);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRopository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        commentRopository.deleteById(id);
    }

    @Override
    public void deleteByReplyto(Long id) {
        commentRopository.deleteByReplyto(id);
    }

    @Override
    public List<CommentResponse> findByPostidAndReplytoIsNull(Long postid) {
        return commentListRopository.findByPostidAndReplytoIsNull(postid);
    }

    @Override
    public List<CommentResponse> findByPostidAndReplyto(Long postid, Long replyto) {
        return commentListRopository.findByPostidAndReplyto(postid, replyto);
    }

    @Override
    public void deleteByPostid(Long postid) {
        commentRopository.deleteByPostid(postid);
    }

    @Override
    public void likeComment(String email, Long commentid) {
        commentListRopository.likeComment(email, commentid);
    }

    @Override
    public void unlikeComment(String email, Long commentid) {
        commentListRopository.unlikeComment(email, commentid);
    }

    @Override
    public int findByEmailAndCommentid(String email, Long commentid) {
        return commentListRopository.findByEmailAndCommentid(email, commentid);
    }

}