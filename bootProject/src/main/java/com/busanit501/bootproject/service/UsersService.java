package com.busanit501.bootproject.service;

import com.busanit501.bootproject.dto.UsersDTO;


import java.util.List;
import java.util.Optional;

public interface UsersService {
    //유저 추가 (생성)
    UsersDTO createUser(UsersDTO userDTO);
    Optional<UsersDTO> getUserById(Long userId);
    //유저 수정
    UsersDTO updateUser(Long userid ,UsersDTO userDTO);
    //유저 삭제
    void deleteUser(Long userid);
    //모든 유저 정보 읽기
    List<UsersDTO> getAllUsers();
    //개인 정보 읽기
    UsersDTO getUserByEmail(String email);
}
