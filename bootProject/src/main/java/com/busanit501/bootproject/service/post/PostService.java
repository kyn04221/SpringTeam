package com.busanit501.bootproject.service.post;

import com.busanit501.bootproject.domain.Category;
import com.busanit501.bootproject.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Page<Post> getAllPosts(Pageable pageable);
    Post getPostById(Long id);
    Post createPost(Post post);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);
    Page<Post> getPostsByCategory(Category category, Pageable pageable);

}
