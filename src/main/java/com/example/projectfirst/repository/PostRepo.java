package com.example.projectfirst.repository;

import com.example.projectfirst.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findByUserId(Long userId);
}
