package com.busanit501.bootproject.controller;


import com.busanit501.bootproject.dto.UsersDTO;
import com.busanit501.bootproject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("message", "회원가입이 완료되었습니다. 로그인 해주세요.");
        return "user/login"; // templates/user/login.html로 이동
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "user/signup";
    }

    @PostMapping("/signup")
    public RedirectView createUser(@RequestBody UsersDTO usersDTO) {
        // 사용자 생성
        UsersDTO createdUser = usersService.createUser(usersDTO);

        // 로그인 페이지로 리디렉션
        RedirectView redirectView = new RedirectView("/user/login");
        redirectView.addStaticAttribute("message", "회원가입이 완료되었습니다. 로그인 해주세요."); // 메시지 전달 (선택 사항)
        return redirectView;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable Long id) {
        return usersService.getUserById(id)
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //수정하기
    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO> updateUser(@PathVariable Long id, @RequestBody UsersDTO usersDTO) {
        UsersDTO updatedUser = usersService.updateUser(id, usersDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //전체 읽기
    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<UsersDTO> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
