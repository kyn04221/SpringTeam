package com.busanit501.bootproject.controller;

import com.busanit501.bootproject.domain.Category;
import com.busanit501.bootproject.domain.Post;
import com.busanit501.bootproject.service.post.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

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

    // 병원 카테고리 게시글만 조회
    @GetMapping("/hospital")
    public String listHospitalPosts(Model model,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> hospitalPosts = postService.getPostsByCategory(Category.EmergencyHospital, pageable);

        model.addAttribute("postsPage", hospitalPosts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", hospitalPosts.getTotalPages());
        return "posts/hospital";
    }

    // 중고장터 카테고리 게시글만 조회
    @GetMapping("/useditems")
    public String listUseditemsPosts(Model model,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> useditemsPosts = postService.getPostsByCategory(Category.UsedItems, pageable);

        model.addAttribute("postsPage", useditemsPosts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", useditemsPosts.getTotalPages());
        return "posts/useditems";
    }
}
