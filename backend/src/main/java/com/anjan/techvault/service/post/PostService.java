package com.anjan.techvault.service.post;

import com.anjan.techvault.domain.post.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post);
    List<Post> getAllPosts();
    List<Post> searchPostsByTitle(String keyword);
    List<Post> getPostsByUserId(Long userId);
    void deletePost(Long id);
}