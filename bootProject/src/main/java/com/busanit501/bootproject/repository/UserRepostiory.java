package com.busanit501.bootproject.repository;

import com.busanit501.bootproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepostiory extends JpaRepository<User, Integer> {
}
