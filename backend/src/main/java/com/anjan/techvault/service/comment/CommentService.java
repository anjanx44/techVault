package com.anjan.techvault.service.comment;

import com.anjan.techvault.domain.comment.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment createComment(Comment comment);
    List<Comment> getCommentsByPostId(Long postId);
    List<Comment> getCommentsByUserId(Long userId);
    Optional<Comment> getCommentById(Long id);
    void deleteComment(Long id);
    Comment updateComment(Long id, Comment comment);
}