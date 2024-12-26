package com.busanit501.bootproject.repository;

import com.busanit501.bootproject.domain.Comment;
import com.busanit501.bootproject.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}