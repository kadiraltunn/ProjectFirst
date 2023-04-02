package com.example.projectfirst.repository;

import com.example.projectfirst.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByUserEntityIdAndPostEntityId(Long userId, Long postId);

    List<CommentEntity> findByUserEntityId(Long userId);

    List<CommentEntity> findByPostEntityId(Long postId);
}
