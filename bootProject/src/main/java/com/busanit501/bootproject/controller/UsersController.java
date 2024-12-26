package com.busanit501.bootproject.controller;


import com.busanit501.bootproject.dto.UsersDTO;
import com.busanit501.bootproject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/users")
public class UsersController {

    @Autowired
    private UsersService usersService;


    @PostMapping
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO usersDTO) {
        UsersDTO createdUser = usersService.createUser(usersDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
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
