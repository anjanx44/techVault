package com.anjan.techvault.domain.like;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class LikeRepository implements PanacheRepository<Like> {

    public Optional<Like> findByUserIdAndPostId(Long userId, Long postId) {
        return find("user.id = ?1 and post.id = ?2", userId, postId).firstResultOptional();
    }

    public Optional<Like> findByUserIdAndCommentId(Long userId, Long commentId) {
        return find("user.id = ?1 and comment.id = ?2", userId, commentId).firstResultOptional();
    }

    public Like save(Like like) {
        if (like.getId() == null) {
            persist(like);
        } else {
            like = getEntityManager().merge(like);
        }
        return like;
    }
}