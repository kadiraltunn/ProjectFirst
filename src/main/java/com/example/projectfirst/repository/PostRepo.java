package com.example.projectfirst.repository;

import com.example.projectfirst.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findByUserEntityId(Long userId);
}
