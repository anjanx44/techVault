package com.anjan.techvault.domain.user;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    // Find user by username
    public Optional<User> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }

    // Check if a username exists
    public boolean existsByUsername(String username) {
        return count("username", username) > 0;
    }

    // Find user by email
    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    // Save a user (handles both insert and update)
    public User save(User user) {
        if (user.getId() == null) {
            persist(user);
        } else {
            user = getEntityManager().merge(user);
        }
        return user;
    }
}