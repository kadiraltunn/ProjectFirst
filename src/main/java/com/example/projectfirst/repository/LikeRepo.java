package com.example.projectfirst.repository;

import com.example.projectfirst.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepo extends JpaRepository<Like, Long> {
    List<Like> findAllByUserIdAndPostId(Long userId, Long postId);

    List<Like> findAllByUserId(Long userId);

    List<Like> findAllByPostId(Long postId);


}
