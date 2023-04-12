package com.example.projectfirst.dto;

import com.example.projectfirst.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponseDTO {

    Long id;
    Long userId;
    String userName;
    String title;
    String text;
    List<LikeResponseDTO> likeResponseDTOList;

    public PostResponseDTO(Post entity, List<LikeResponseDTO> likes) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.likeResponseDTOList = likes;
    }
}
