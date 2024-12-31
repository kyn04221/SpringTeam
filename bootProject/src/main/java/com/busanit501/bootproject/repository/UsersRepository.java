package com.busanit501.bootproject.repository;

import com.busanit501.bootproject.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
   Optional<Users> findByEmail(String email); // 이메일로 사용자 찾기
}