package com.busanit501.bootproject.controller;

import com.busanit501.bootproject.domain.Category;
import com.busanit501.bootproject.domain.Post;
import com.busanit501.bootproject.service.post.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Log4j2
@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
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
