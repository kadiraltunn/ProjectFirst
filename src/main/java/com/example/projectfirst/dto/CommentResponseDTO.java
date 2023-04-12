package com.example.projectfirst.dto;

import com.example.projectfirst.entity.Comment;
import lombok.Data;

@Data
public class CommentResponseDTO {

    Long id;
    Long userId;
    String userName;
    String text;

    public CommentResponseDTO(Comment comment){
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.userName = comment.getUser().getUserName();
        this.text = comment.getText();
    }
}
