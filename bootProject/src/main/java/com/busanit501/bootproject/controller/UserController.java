package com.busanit501.bootproject.controller;

import com.busanit501.bootproject.dto.UserDTO;
import com.busanit501.bootproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm(Model model) {
        return "user/login"; // login.html로 이동
    }

    // 로그인 처리
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        // 로그인 로직 구현 (예: 인증 서비스 호출)
        // 성공 시 사용자 세션 설정, 실패 시 오류 메시지 반환
        return ResponseEntity.ok().build();
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String signupForm(Model model) {
        return "user/signup"; // signup.html로 이동
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@ModelAttribute UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
        //리다이렉트로 데이터 탑재후 login페이지로 이동시키면 됌
    }

    // 이메일 중복 확인
    @PostMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean exists = userService.checkEmailExists(email);
        return ResponseEntity.ok().body(Map.of("exists", exists));
    }

    // 사용자 생성 (Create)
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    // 사용자 조회 (Read)
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    // 모든 사용자 조회 (Read)
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // 사용자 업데이트 (Update)
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // 사용자 삭제 (Delete)
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
