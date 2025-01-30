package com.anjan.techvault.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username
    Optional<User> findByUsername(String username);

    // Check if a username exists
    boolean existsByUsername(String username);

    // Find user by email
    Optional<User> findByEmail(String email);

    // No need to add getUserById and deleteUser, as JpaRepository already provides these methods
    // Optional<User> findById(Long id); // Provided by JpaRepository
    // void deleteById(Long id); // Provided by JpaRepository
}



