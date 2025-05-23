package com.anjan.techvault.service.user;

import com.anjan.techvault.domain.user.User;
import com.anjan.techvault.domain.user.UserRepository;
import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Collections;

/**
 * Custom user details service for Quarkus security.
 * This service is responsible for loading user details from the database.
 */
@ApplicationScoped
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    @Inject
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Load a user by username.
     *
     * @param username the username to look up
     * @return the user object
     * @throws UnauthorizedException if the user is not found
     */
    public User loadUserByUsername(String username) throws UnauthorizedException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UnauthorizedException("User not found with username: " + username));
    }
}