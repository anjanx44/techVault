package com.anjan.techvault.service.like;

import com.anjan.techvault.domain.like.Like;

import java.util.Optional;

public interface LikeService {
    Like addLike(Like like);
    Optional<Like> getLikeByUserAndPost(Long userId, Long postId);
    Optional<Like> getLikeByUserAndComment(Long userId, Long commentId);
    void removeLike(Long id);
}
