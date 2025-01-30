package com.anjan.techvault.service.user;
import com.anjan.techvault.domain.user.User;

import java.util.Optional;

public interface UserService {

    // Method to save or create a new user
    void createUser(User user);

    // Method to get a user by their ID
    Optional<User> getUserById(Long id);

    // Method to delete a user by their ID
    void deleteUser(Long id);

    // Existing methods
    void save(User user);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);
}
