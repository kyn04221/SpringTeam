package com.busanit501.bootproject;

import com.busanit501.bootproject.domain.Category;
import com.busanit501.bootproject.domain.Post;
import com.busanit501.bootproject.repository.PostRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testInsertPosts() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
            Post post = Post.builder()
                    .title("테스트 게시글 " + i)
                    .content("테스트 내용입니다. " + i)
                    .category(Category.valueOf("FreeBoard"))
                    .build();
            postRepository.save(post);
        });
        log.info("50개의 게시글이 저장되었습니다.");
    }

    @Test
    public void testSelectOne() {
        Long id = 1L;
        Optional<Post> result = postRepository.findById(id);
        Post post = result.orElseThrow();
        log.info("조회된 게시글: " + post);
    }

    @Test
    public void testSelectAll() {
        List<Post> result = postRepository.findAll();
        result.forEach(post -> log.info(post.toString()));
    }

    @Test
    public void testUpdatePost() {
        Long id = 1L;
        Optional<Post> result = postRepository.findById(id);
        Post post = result.orElseThrow();

        post.setTitle("수정된 제목");
        post.setContent("수정된 내용입니다.");
        postRepository.save(post);

        log.info("게시글 수정 완료: " + post);
    }

    @Test
    public void testDeletePost() {
        Long id = 2L;
        postRepository.deleteById(id);
        log.info("게시글 삭제 완료, ID: " + id);
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("postId").descending());
        Page<Post> result = postRepository.findAll(pageable);

        log.info("전체 게시글 수: " + result.getTotalElements());
        log.info("전체 페이지 수: " + result.getTotalPages());
        log.info("현재 페이지: " + result.getNumber());
        result.getContent().forEach(post -> log.info(post.toString()));
    }
}