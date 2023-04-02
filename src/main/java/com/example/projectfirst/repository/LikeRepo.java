package com.example.projectfirst.repository;

import com.example.projectfirst.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<LikeEntity, Long> {
}
