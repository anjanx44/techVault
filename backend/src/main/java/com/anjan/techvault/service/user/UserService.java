package com.anjan.techvault.service.user;

import com.anjan.techvault.domain.user.User;

import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    void deleteUser(Long id);
}

