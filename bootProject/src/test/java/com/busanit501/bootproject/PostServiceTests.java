package com.busanit501.bootproject;

import com.busanit501.bootproject.domain.Category;
import com.busanit501.bootproject.domain.Post;
import com.busanit501.bootproject.repository.PostRepository;
import com.busanit501.bootproject.service.post.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest  // SpringBoot 통합 테스트
@Transactional   // 각 테스트 후 롤백 (DB에 실제 데이터가 남지 않음)
public class PostServiceTests {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    private Post testPost;

    @BeforeEach
    public void setUp() {
        postRepository.deleteAll();
        testPost = Post.builder()
                .title("테스트 게시글")
                .content("테스트 내용입니다.")
                .category(Category.valueOf("FreeBoard"))
                .build();
        postRepository.save(testPost);  // 사전 데이터 저장
    }

    @Test
    @DisplayName("게시글 전체 조회 테스트")
    public void testGetAllPosts() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);  // 페이지 번호(0), 페이지 크기(10)

        // When
        Page<Post> postsPage = postService.getAllPosts(pageable);

        // Then
        assertThat(postsPage).isNotEmpty();
        assertThat(postsPage.getContent().size()).isGreaterThanOrEqualTo(1);
        assertThat(postsPage.getContent().get(0).getTitle()).isEqualTo("테스트 게시글");
    }

    @Test
    @DisplayName("게시글 생성 테스트")
    public void testCreatePost() {
        // Given
        Post newPost = Post.builder()
                .title("새로운 게시글")
                .content("새로운 내용입니다.")
                .category(Category.valueOf("FreeBoard"))
                .build();

        // When
        Post savedPost = postService.createPost(newPost);

        // Then
        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getTitle()).isEqualTo("새로운 게시글");
        assertThat(postRepository.count()).isEqualTo(2);  // 게시글 2개 (setUp에서 1개 + 추가 1개)
    }

    @Test
    @DisplayName("특정 게시글 조회 테스트 - 성공")
    public void testGetPostById_Success() {
        // When
        Post foundPost = postService.getPostById(testPost.getPostId());

        // Then
        assertThat(foundPost).isNotNull();
        assertThat(foundPost.getTitle()).isEqualTo("테스트 게시글");
    }

    @Test
    @DisplayName("특정 게시글 조회 테스트 - 실패")
    public void testGetPostById_Fail() {
        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            postService.getPostById(999L);  // 존재하지 않는 ID
        });
        assertThat(exception.getMessage()).isEqualTo("Post not found");
    }

    @Test
    @DisplayName("게시글 수정 테스트 - 성공")
    public void testUpdatePost_Success() {
        // Given
        Post updatedPost = Post.builder()
                .title("수정된 제목")
                .content("수정된 내용")
                .category(Category.valueOf("FreeBoard"))
                .build();

        // When
        Post result = postService.updatePost(testPost.getPostId(), updatedPost);

        // Then
        assertThat(result.getTitle()).isEqualTo("수정된 제목");
        assertThat(result.getContent()).isEqualTo("수정된 내용");
    }

    @Test
    @DisplayName("게시글 수정 테스트 - 실패")
    public void testUpdatePost_Fail() {
        // Given
        Post updatedPost = Post.builder()
                .title("수정된 제목")
                .content("수정된 내용")
                .category(Category.valueOf("FreeBoard"))
                .build();

        // When & Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            postService.updatePost(999L, updatedPost);  // 존재하지 않는 ID
        });
        assertThat(exception.getMessage()).isEqualTo("Post not found");
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    public void testDeletePost() {
        // When
        postService.deletePost(testPost.getPostId());

        // Then
        Optional<Post> deletedPost = postRepository.findById(testPost.getPostId());
        assertThat(deletedPost).isEmpty();
    }
}