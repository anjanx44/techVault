package com.anjan.techvault.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId); // Find comments for a specific post
    List<Comment> findByUserId(Long userId); // Find comments by a specific user
}
