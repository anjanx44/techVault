package com.anjan.techvault.service.comment;

import com.anjan.techvault.domain.comment.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);
    List<Comment> getCommentsByPostId(Long postId);
    List<Comment> getCommentsByUserId(Long userId);
    void deleteComment(Long id);
}