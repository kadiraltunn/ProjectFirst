package com.example.projectfirst.service;

import com.example.projectfirst.dto.LikeResponseDTO;
import com.example.projectfirst.dto.PostCreateDTO;
import com.example.projectfirst.dto.PostResponseDTO;
import com.example.projectfirst.dto.PostUpdateDTO;
import com.example.projectfirst.entity.Post;
import com.example.projectfirst.entity.User;
import com.example.projectfirst.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepo postRepo;
    private UserService userService;
    private LikeService likeService;

    public PostService(PostRepo postRepo, UserService userService) {
        this.postRepo = postRepo;
        this.userService = userService;
    }
    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

    public List<PostResponseDTO> getAllPostsWithParams(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()) {
            list = postRepo.findByUserId(userId.get());
        }else
            list = postRepo.findAll();
        return list.stream().map(p -> {
            List<LikeResponseDTO> likes = likeService.getAllLikesWithParams(Optional.ofNullable(null), Optional.of(p.getId()));
            return new PostResponseDTO(p, likes);}).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {
        return postRepo.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateDTO newPostDTO) {
        User user = userService.getOneUserById(newPostDTO.getUserId());
        if (user == null)
            return null;
        Post toSave = new Post();
        toSave.setId(newPostDTO.getId());
        toSave.setText(newPostDTO.getText());
        toSave.setTitle(newPostDTO.getTitle());
        toSave.setUser(user);
        return postRepo.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateDTO updateDTO) {
        Optional<Post> postEntity = postRepo.findById(postId);
        if (postEntity.isPresent()){
            Post toUpdatePost = postEntity.get();
            toUpdatePost.setText(updateDTO.getText());
            toUpdatePost.setTitle(updateDTO.getTitle());
            postRepo.save(toUpdatePost);
            return toUpdatePost;
        }
        return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepo.deleteById(postId);
    }


}
