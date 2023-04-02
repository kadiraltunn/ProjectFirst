package com.example.projectfirst.controller;

import com.example.projectfirst.dto.PostCreateDTO;
import com.example.projectfirst.dto.PostUpdateDTO;
import com.example.projectfirst.entity.PostEntity;
import com.example.projectfirst.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostEntity> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public PostEntity createOnePost(@RequestBody PostCreateDTO newPostDTO){
        return postService.createOnePost(newPostDTO);
    }

    @GetMapping("/{postId}")
    public PostEntity getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }

    @PutMapping("/{postId}")
    public PostEntity updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateDTO updatePost){
        return postService.updateOnePostById(postId, updatePost);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePostById(postId);
    }


}
