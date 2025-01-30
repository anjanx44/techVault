package com.anjan.techvault.service.user;

import com.anjan.techvault.domain.user.User;
import com.anjan.techvault.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user); // Save or create a new user
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id); // Find user by their ID
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id); // Delete a user by their ID
    }

    @Override
    public void save(User user) {
        userRepository.save(user); // Save the user to the database
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username); // Find a user by username
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username); // Check if username already exists
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email); // Find a user by email
    }
}
