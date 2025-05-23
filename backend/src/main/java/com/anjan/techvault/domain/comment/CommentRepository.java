package com.anjan.techvault.domain.comment;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CommentRepository implements PanacheRepository<Comment> {

    public List<Comment> findByPostId(Long postId) {
        return list("post.id", postId);
    }

    public List<Comment> findByUserId(Long userId) {
        return list("user.id", userId);
    }

    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            persist(comment);
        } else {
            comment = getEntityManager().merge(comment);
        }
        return comment;
    }
}