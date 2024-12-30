package com.busanit501.bootproject.service.post;

import com.busanit501.bootproject.domain.Category;
import com.busanit501.bootproject.domain.Post;
import com.busanit501.bootproject.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post updatePost(Long id, Post post) {
        Post existingPost = getPostById(id);
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        existingPost.setCategory(post.getCategory());
        return postRepository.save(existingPost);
    }

    @Override
    public Page<Post> getPostsByCategory(Category category, Pageable pageable) {
        return postRepository.findByCategory(category, pageable);
    }
}
