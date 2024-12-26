package com.busanit501.bootproject.service.post;

import com.busanit501.bootproject.domain.Post;
import com.busanit501.bootproject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // 게시글 수정 로직 추가
    @Override
    public Post updatePost(Long id, Post post) {
        // 기존 게시글이 존재하는지 확인
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // 기존 게시글에 새로운 데이터 덮어쓰기
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        existingPost.setCategory(post.getCategory());

        return postRepository.save(existingPost);
    }
}
