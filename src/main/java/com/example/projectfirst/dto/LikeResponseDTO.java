package com.example.projectfirst.dto;

import com.example.projectfirst.entity.Like;
import lombok.Data;

@Data
public class LikeResponseDTO {

    Long id;
    Long userId;
    Long postId;

    public LikeResponseDTO(Like likeEntity){
        this.id = likeEntity.getId();
        this.userId = likeEntity.getUser().getId();
        this.postId = likeEntity.getPost().getId();
    }
}
