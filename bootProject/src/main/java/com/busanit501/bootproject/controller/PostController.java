package com.busanit501.bootproject.controller;

import com.busanit501.bootproject.domain.Category;
import com.busanit501.bootproject.domain.Post;
import com.busanit501.bootproject.domain.Users;
import com.busanit501.bootproject.repository.UsersRepository;
import com.busanit501.bootproject.service.post.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;

@Log4j2
@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UsersRepository usersRepository;

    public PostController(PostService postService, UsersRepository usersRepository) {
        this.postService = postService;
        this.usersRepository = usersRepository;
    }

    // 글쓰기 GET
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/register";
    }

//    // 글쓰기 Post -> 로그인 필요함 이걸로 할거임 나중에
//    @PostMapping("/register")
//    public String registerPost(@ModelAttribute Post post, Principal principal) {
//        Users user = usersRepository.findByEmail(principal.getName())
//                .orElseThrow(() -> new IllegalStateException("유저를 찾을 수 없습니다."));
//
//        post.setUser(user);
//        postService.createPost(post);
//
//        return "redirect:/posts";  // 글 작성 후 목록으로 리다이렉트
//    }

    // 테스트용 임시로 유저데이터 이용해서 테스트. 이거 나중에 버릴거에요
    @PostMapping("/register")
    public String registerPost(@RequestBody Post post) {
        log.info("Received Post: " + post);
        log.info("Category: " + post.getCategory());

        if (post.getCategory() == null) {
            throw new IllegalArgumentException("Category is null");
        }

        Users user = usersRepository.findById(1L)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        post.setUser(user);
        postService.createPost(post);
        return "redirect:/posts";
    }


    // 📌 게시글 목록 조회 (페이징)
    @GetMapping
    public String listPosts(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postsPage = postService.getAllPosts(pageable);

        model.addAttribute("postsPage", postsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postsPage.getTotalPages());
        return "posts/list";
    }

    // 📌 게시글 상세 조회
    @GetMapping("/{id}")
    public String getPostDetail(@PathVariable Long id,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("currentPage", page);
        return "posts/detail";
    }

    // 📌 게시글 수정 페이지
    @GetMapping("/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    // 📌 게시글 수정 처리 (AJAX 요청)
    @PutMapping("/update/{id}")
    @ResponseBody
    public String updatePost(@PathVariable Long id, @RequestBody Post post) {
        log.info("Update 요청: " + id);
        postService.updatePost(id, post);
        return "success";
    }

    // 📌 게시글 삭제 처리 (AJAX 요청)
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deletePost(@PathVariable Long id) {
        log.info("Delete 요청: " + id);
        postService.deletePost(id);
        return "success";
    }

    @GetMapping("/category/hospital")
    public String hospitalPage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postsPage = postService.getPostsByCategory(Category.EmergencyHospital, pageable);

        model.addAttribute("postsPage", postsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postsPage.getTotalPages());
        return "posts/hospital";
    }

    @GetMapping("/category/useditems")
    public String usedItemsPage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "8") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postsPage = postService.getPostsByCategory(Category.UsedItems, pageable);

        model.addAttribute("postsPage", postsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postsPage.getTotalPages());
        return "posts/useditems";
    }


}
