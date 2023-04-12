package com.example.projectfirst.service;

import com.example.projectfirst.dto.LikeCreateDTO;
import com.example.projectfirst.dto.LikeResponseDTO;
import com.example.projectfirst.entity.Like;
import com.example.projectfirst.entity.Post;
import com.example.projectfirst.entity.User;
import com.example.projectfirst.repository.LikeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private LikeRepo likeRepo;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepo likeRepo, UserService userService, PostService postService) {
        this.likeRepo = likeRepo;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeResponseDTO> getAllLikesWithParams(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if (userId.isPresent() && postId.isPresent())
            list = likeRepo.findAllByUserIdAndPostId(userId.get(), postId.get());
        else if(userId.isPresent())
            list = likeRepo.findAllByUserId(userId.get());
        else if(postId.isPresent())
            list = likeRepo.findAllByPostId(postId.get());
        else
            list = likeRepo.findAll();
        return list.stream().map(like -> new LikeResponseDTO((like))).collect(Collectors.toList());
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepo.findById(likeId).orElse(null);
    }

    public Like createOneLike(LikeCreateDTO newLikeDTO) {
        User user = userService.getOneUserById(newLikeDTO.getUserId());
        Post post = postService.getOnePostById(newLikeDTO.getPostId());
        if (user != null && post != null){
            Like toSaveLike = new Like();
            toSaveLike.setUser(user);
            toSaveLike.setPost(post);
            return likeRepo.save(toSaveLike);
        }else
            return null;
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepo.deleteById(likeId);
    }
}
