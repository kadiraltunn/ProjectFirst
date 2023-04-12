package com.example.projectfirst.service;

import com.example.projectfirst.dto.CommentCreateDTO;
import com.example.projectfirst.dto.CommentResponseDTO;
import com.example.projectfirst.dto.CommentUpdateDTO;
import com.example.projectfirst.entity.Comment;
import com.example.projectfirst.entity.Post;
import com.example.projectfirst.entity.User;
import com.example.projectfirst.repository.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentRepo commentRepo;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepo commentRepo, UserService userService, PostService postService) {
        this.commentRepo = commentRepo;
        this.userService = userService;
        this.postService = postService;
    }

    public List<CommentResponseDTO> getAllCommentsWithParams(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> comments;
        if (userId.isPresent() && postId.isPresent())
            comments = commentRepo.findByUserIdAndPostId(userId.get(), postId.get());
        else if(userId.isPresent())
            comments = commentRepo.findByUserId(userId.get());
        else if (postId.isPresent())
            comments = commentRepo.findByPostId(postId.get());
        else
            comments = commentRepo.findAll();
        return comments.stream().map(comment -> new CommentResponseDTO(comment)).collect(Collectors.toList());
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepo.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateDTO newCommentDTO) {
        User user = userService.getOneUserById(newCommentDTO.getUserId());
        Post post = postService.getOnePostById(newCommentDTO.getPostId());
        if (user != null && post != null){
            Comment toSaveComment = new Comment();
            toSaveComment.setId(newCommentDTO.getId());
            toSaveComment.setPost(post);
            toSaveComment.setUser(user);
            toSaveComment.setText(newCommentDTO.getText());
            return commentRepo.save(toSaveComment);
        }else
            return null;
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateDTO updateComment) {
        Optional<Comment> commentEntity = commentRepo.findById(commentId);
        if (commentEntity.isPresent()){
            Comment toUpdateComment = commentEntity.get();
            toUpdateComment.setText(updateComment.getText());
            return commentRepo.save(toUpdateComment);
        }else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepo.deleteById(commentId);
    }
}
