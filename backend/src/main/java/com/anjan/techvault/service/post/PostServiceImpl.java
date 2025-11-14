package com.anjan.techvault.service.post;

import com.anjan.techvault.domain.post.Post;
import com.anjan.techvault.domain.post.PostRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Inject
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }
        if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Post title cannot be empty");
        }
        if (post.getContent() == null || post.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Post content cannot be empty");
        }
        if (post.getUser() == null) {
            throw new IllegalArgumentException("Post must be associated with a user");
        }

        // Ensure createdAt is set
        if (post.getCreatedAt() == null) {
            post.setCreatedAt(LocalDateTime.now());
        }

        // Set updatedAt to current time
        post.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.listAll();
    }

    @Override
    public List<Post> searchPostsByTitle(String keyword) {
        if (keyword == null) {
            throw new IllegalArgumentException("Search keyword cannot be null");
        }
        return postRepository.findByTitleContaining(keyword);
    }

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return postRepository.findByUserId(userId);
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Post ID cannot be null");
        }
        return postRepository.find("id", id).firstResultOptional();
    }

    @Override
    public void deletePost(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Post ID cannot be null");
        }

        // Check if post exists before deleting
        if (postRepository.count("id", id) == 0) {
            throw new NotFoundException("Post with ID " + id + " not found");
        }

        postRepository.delete("id", id);
    }

    @Override
    public Post updatePost(Long id, Post post) {
        if (id == null) {
            throw new IllegalArgumentException("Post ID cannot be null");
        }
        if (post == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }
        if (post.getTitle() == null || post.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Post title cannot be empty");
        }
        if (post.getContent() == null || post.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Post content cannot be empty");
        }

        // Check if post exists
        Optional<Post> existingPost = postRepository.find("id", id).firstResultOptional();
        if (!existingPost.isPresent()) {
            throw new NotFoundException("Post with ID " + id + " not found");
        }

        // Update the existing post
        Post postToUpdate = existingPost.get();
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setContent(post.getContent());
        postToUpdate.setTags(post.getTags());
        postToUpdate.setPublished(post.getPublished());

        // Don't update user relationship
        // Don't update createdAt timestamp

        // Update updatedAt timestamp
        postToUpdate.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(postToUpdate);
    }

    @Override
    public List<Post> getFeaturedPosts() {
        return postRepository.find("featured = true ORDER BY createdAt DESC").list();
    }
}