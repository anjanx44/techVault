package com.anjan.techvault.service.comment;

import com.anjan.techvault.domain.comment.Comment;
import com.anjan.techvault.domain.comment.CommentRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Inject
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        if (comment.getContent() == null || comment.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Comment content cannot be empty");
        }
        if (comment.getPost() == null) {
            throw new IllegalArgumentException("Comment must be associated with a post");
        }
        if (comment.getUser() == null) {
            throw new IllegalArgumentException("Comment must be associated with a user");
        }

        // Ensure createdAt is set
        if (comment.getCreatedAt() == null) {
            comment.setCreatedAt(LocalDateTime.now());
        }

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        if (postId == null) {
            throw new IllegalArgumentException("Post ID cannot be null");
        }
        return commentRepository.findByPostId(postId);
    }

    @Override
    public List<Comment> getCommentsByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return commentRepository.findByUserId(userId);
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Comment ID cannot be null");
        }
        return commentRepository.find("id", id).firstResultOptional();
    }

    @Override
    public void deleteComment(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Comment ID cannot be null");
        }

        // Check if comment exists before deleting
        if (commentRepository.count("id", id) == 0) {
            throw new NotFoundException("Comment with ID " + id + " not found");
        }

        commentRepository.delete("id", id);
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        if (id == null) {
            throw new IllegalArgumentException("Comment ID cannot be null");
        }
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        if (comment.getContent() == null || comment.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Comment content cannot be empty");
        }

        // Check if comment exists
        Optional<Comment> existingComment = commentRepository.find("id", id).firstResultOptional();
        if (!existingComment.isPresent()) {
            throw new NotFoundException("Comment with ID " + id + " not found");
        }

        // Update the existing comment
        Comment commentToUpdate = existingComment.get();
        commentToUpdate.setContent(comment.getContent());

        // Don't update user, post, or parent comment relationships
        // Don't update createdAt timestamp

        return commentRepository.save(commentToUpdate);
    }
}