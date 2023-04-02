package com.example.projectfirst.service;

import com.example.projectfirst.dto.CommentCreateDTO;
import com.example.projectfirst.dto.CommentUpdateDTO;
import com.example.projectfirst.entity.CommentEntity;
import com.example.projectfirst.entity.PostEntity;
import com.example.projectfirst.entity.UserEntity;
import com.example.projectfirst.repository.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<CommentEntity> getAllCommentsWithParams(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent())
            return commentRepo.findByUserEntityIdAndPostEntityId(userId.get(), postId.get());
        else if(userId.isPresent())
            return commentRepo.findByUserEntityId(userId.get());
        else if (postId.isPresent())
            return commentRepo.findByPostEntityId(postId.get());
        else return commentRepo.findAll();
    }

    public CommentEntity getOneCommentById(Long commentId) {
        return commentRepo.findById(commentId).orElse(null);
    }

    public CommentEntity createOneComment(CommentCreateDTO newCommentDTO) {
        UserEntity userEntity = userService.getOneUserById(newCommentDTO.getUserId());
        PostEntity postEntity = postService.getOnePostById(newCommentDTO.getPostId());
        if (userEntity != null && postEntity != null){
            CommentEntity toSaveComment = new CommentEntity();
            toSaveComment.setId(newCommentDTO.getId());
            toSaveComment.setPostEntity(postEntity);
            toSaveComment.setUserEntity(userEntity);
            toSaveComment.setText(newCommentDTO.getText());
            return commentRepo.save(toSaveComment);
        }else
            return null;
    }

    public CommentEntity updateOneCommentById(Long commentId, CommentUpdateDTO updateComment) {
        Optional<CommentEntity> commentEntity = commentRepo.findById(commentId);
        if (commentEntity.isPresent()){
            CommentEntity toUpdateComment = commentEntity.get();
            toUpdateComment.setText(updateComment.getText());
            return commentRepo.save(toUpdateComment);
        }else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepo.deleteById(commentId);
    }
}
