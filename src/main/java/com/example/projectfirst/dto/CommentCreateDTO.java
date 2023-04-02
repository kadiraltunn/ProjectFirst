package com.example.projectfirst.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    Long id;
    Long userId;
    Long postId;
    String text;
}
