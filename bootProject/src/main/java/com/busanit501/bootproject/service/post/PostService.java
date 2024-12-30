package com.busanit501.bootproject.service.post;

import com.busanit501.bootproject.domain.Category;
import com.busanit501.bootproject.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    Page<Post> getAllPosts(Pageable pageable);
    Post createPost(Post post);
    Post getPostById(Long id);
    void deletePost(Long id);
    Post updatePost(Long id, Post post);

    // Enum 타입으로 카테고리 조회
    Page<Post> getPostsByCategory(Category category, Pageable pageable);
}
