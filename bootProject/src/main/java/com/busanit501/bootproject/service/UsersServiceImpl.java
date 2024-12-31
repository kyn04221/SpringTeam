package com.busanit501.bootproject.service;

import com.busanit501.bootproject.domain.Users;


import com.busanit501.bootproject.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users registerUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 비밀번호 암호화
        return usersRepository.save(user);
    }

    @Override
    public Users loginUser(String email, String password) {
        Optional<Users> userOptional = usersRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            // 비밀번호 검증
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user; // 로그인 성공
            }
        }
        return null; // 로그인 실패
    }

    @Override
    public boolean emailExists(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }
}