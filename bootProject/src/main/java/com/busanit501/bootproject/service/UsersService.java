package com.busanit501.bootproject.service;


import com.busanit501.bootproject.domain.Users;

public interface UsersService {
    Users registerUser(Users user);
    Users loginUser(String email, String password);
    boolean emailExists(String email);
}