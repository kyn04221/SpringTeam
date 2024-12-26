package com.busanit501.bootproject.service.post;

import com.busanit501.bootproject.domain.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();  // 모든 게시글 조회
    Post createPost(Post post); // 게시글 작성
    Post getPostById(Long id);  // 특정 게시글 조회
    void deletePost(Long id);   // 게시글 삭제
    Post updatePost(Long id, Post post);  // 게시글 수정 (추가)
}
