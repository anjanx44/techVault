package com.anjan.techvault.domain.post;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {

    public List<Post> findByUserId(Long userId) {
        return list("user.id", userId);
    }

    public List<Post> findByTitleContaining(String keyword) {
        return list("LOWER(title) LIKE LOWER(?1)", "%" + keyword + "%");
    }

    public Post save(Post post) {
        if (post.getId() == null) {
            persist(post);
        } else {
            post = getEntityManager().merge(post);
        }
        return post;
    }
}