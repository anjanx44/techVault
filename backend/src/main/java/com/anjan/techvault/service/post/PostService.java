package com.anjan.techvault.service.post;

import com.anjan.techvault.domain.post.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Post post);
    List<Post> getAllPosts();
    List<Post> searchPostsByTitle(String keyword);
    List<Post> getPostsByUserId(Long userId);
    Optional<Post> getPostById(Long id);
    void deletePost(Long id);
    Post updatePost(Long id, Post post);
}