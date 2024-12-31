package com.busanit501.bootproject.controller;

import com.busanit501.bootproject.domain.Users;
import com.busanit501.bootproject.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/users/signup")
    public String signupPage() {
        return "signup"; // signup.html을 렌더링
    }

    @PostMapping("/users/signup")
    public String signupUser(Users user) {
        usersService.registerUser(user);
        return "redirect:/users/login"; // 로그인 페이지로 리다이렉트
    }
    @PostMapping("/users/check-email")
    @ResponseBody
    public Map<String, Object> checkEmail(@RequestParam String email) {
        Map<String, Object> response = new HashMap<>();
        boolean exists = usersService.emailExists(email);
        response.put("exists", exists);
        return response;
    }

    @GetMapping("/users/login")
    public String loginPage(Model model) {
        return "login"; // login.html을 렌더링
    }

    @PostMapping("/users/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
        Users user = usersService.loginUser(email, password);
        if (user != null) {
            // 로그인 성공 시 세션에 사용자 정보 저장
            return "redirect:/home"; // 메인 페이지로 리다이렉트
        } else {
            model.addAttribute("message", "이메일 또는 비밀번호가 잘못되었습니다.");
            return "login"; // 로그인 실패 시 로그인 페이지로 다시 돌아감
        }
    }
}
