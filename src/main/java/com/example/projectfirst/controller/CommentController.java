package com.example.projectfirst.controller;

import com.example.projectfirst.dto.CommentCreateDTO;
import com.example.projectfirst.dto.CommentUpdateDTO;
import com.example.projectfirst.entity.CommentEntity;
import com.example.projectfirst.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentEntity> getAllComments(@RequestParam Optional<Long> userId,
                                              @RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWithParams(userId, postId);
    }

    @GetMapping("/{commentId}")
    public CommentEntity getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PostMapping
    public CommentEntity createOneComment(@RequestBody CommentCreateDTO newCommentDTO){
        return commentService.createOneComment(newCommentDTO);
    }

    @PutMapping("/{commentId}")
    public CommentEntity updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateDTO updateComment){
        return commentService.updateOneCommentById(commentId, updateComment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneCommentById(commentId);
    }


}
