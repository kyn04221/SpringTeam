package com.busanit501.bootproject.controller;

import com.busanit501.bootproject.domain.Post;
import com.busanit501.bootproject.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 전체 조회
    @GetMapping
    public String list(Model model) {
        System.out.println("게시글 목록 페이지 호출");  // 디버그용
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts/list";
    }

    // 게시글 작성 폼
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/register";
    }

    // 게시글 작성 처리
    @PostMapping("/register")
    public String registerPost(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        Post savedPost = postService.createPost(post);
        redirectAttributes.addFlashAttribute("message", "게시글이 등록되었습니다.");
        return "redirect:/posts";
    }

    // 게시글 수정 폼
    @GetMapping("/update/{id}")
    public String updateGet(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Post post = postService.getPostById(id);
            model.addAttribute("post", post);
            return "posts/update";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "게시글을 찾을 수 없습니다.");
            return "redirect:/posts";
        }
    }

    // 게시글 수정 처리
    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        try {
            post.setPostId(id);
            postService.updatePost(id, post);
            redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "게시글 수정에 실패했습니다.");
        }
        return "redirect:/posts";
    }

    // 게시글 삭제 처리
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            postService.deletePost(id);
            redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "게시글 삭제에 실패했습니다.");
        }
        return "redirect:/posts";
    }
}
