package com.example.projectfirst.controller;

import com.example.projectfirst.dto.CommentCreateDTO;
import com.example.projectfirst.dto.CommentResponseDTO;
import com.example.projectfirst.dto.CommentUpdateDTO;
import com.example.projectfirst.entity.Comment;
import com.example.projectfirst.exception.CommentNotFoundException;
import com.example.projectfirst.service.CommentService;
import org.springframework.http.HttpStatus;
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
    public List<CommentResponseDTO> getAllComments(@RequestParam Optional<Long> userId,
                                                   @RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWithParams(userId, postId);
    }

    @GetMapping("/{commentId}")
    public CommentResponseDTO getOneComment(@PathVariable Long commentId){
        Comment comment = commentService.getOneCommentById(commentId);
        if (comment == null)
            throw new CommentNotFoundException();
        return new CommentResponseDTO(comment);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateDTO newCommentDTO){
        return commentService.createOneComment(newCommentDTO);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateDTO updateComment){
        Comment comment = commentService.getOneCommentById(commentId);
        if (comment == null)
            throw new CommentNotFoundException();
        return commentService.updateOneCommentById(commentId, updateComment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        Comment comment = commentService.getOneCommentById(commentId);
        if (comment == null)
            throw new CommentNotFoundException();
        commentService.deleteOneCommentById(commentId);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleCommentNotFound(){

    }
}
