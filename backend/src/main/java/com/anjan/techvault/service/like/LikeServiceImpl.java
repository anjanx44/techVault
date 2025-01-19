package com.anjan.techvault.service.like;

import com.anjan.techvault.domain.like.Like;
import com.anjan.techvault.domain.like.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public Like addLike(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Optional<Like> getLikeByUserAndPost(Long userId, Long postId) {
        return likeRepository.findByUserIdAndPostId(userId, postId);
    }

    @Override
    public Optional<Like> getLikeByUserAndComment(Long userId, Long commentId) {
        return likeRepository.findByUserIdAndCommentId(userId, commentId);
    }

    @Override
    public void removeLike(Long id) {
        likeRepository.deleteById(id);
    }
}
