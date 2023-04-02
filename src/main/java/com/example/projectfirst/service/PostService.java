package com.example.projectfirst.service;

import com.example.projectfirst.dto.PostCreateDTO;
import com.example.projectfirst.dto.PostUpdateDTO;
import com.example.projectfirst.entity.PostEntity;
import com.example.projectfirst.entity.UserEntity;
import com.example.projectfirst.repository.PostRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepo postRepo;
    private UserService userService;

    public PostService(PostRepo postRepo, UserService userService) {
        this.postRepo = postRepo;
        this.userService = userService;
    }

    public List<PostEntity> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepo.findByUserEntityId(userId.get());
        }
        return postRepo.findAll();
    }

    public PostEntity getOnePostById(Long postId) {
        return postRepo.findById(postId).orElse(null);
    }

    public PostEntity createOnePost(PostCreateDTO newPostDTO) {
        UserEntity userEntity = userService.getOneUser(newPostDTO.getUserId());
        if (userEntity == null)
            return null;
        PostEntity toSave = new PostEntity();
        toSave.setId(newPostDTO.getId());
        toSave.setText(newPostDTO.getText());
        toSave.setTitle(newPostDTO.getTitle());
        toSave.setUserEntity(userEntity);
        return postRepo.save(toSave);
    }

    public PostEntity updateOnePostById(Long postId, PostUpdateDTO updateDTO) {
        Optional<PostEntity> postEntity = postRepo.findById(postId);
        if (postEntity.isPresent()){
            PostEntity toUpdate = postEntity.get();
            toUpdate.setText(updateDTO.getText());
            toUpdate.setTitle(updateDTO.getTitle());
            postRepo.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepo.deleteById(postId);
    }


}
