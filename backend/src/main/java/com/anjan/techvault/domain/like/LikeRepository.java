package com.anjan.techvault.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndPostId(Long userId, Long postId); // Find like by user and post
    Optional<Like> findByUserIdAndCommentId(Long userId, Long commentId); // Find like by user and comment
}
