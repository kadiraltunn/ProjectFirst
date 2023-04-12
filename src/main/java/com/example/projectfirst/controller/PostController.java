package com.example.projectfirst.controller;

import com.example.projectfirst.dto.PostCreateDTO;
import com.example.projectfirst.dto.PostResponseDTO;
import com.example.projectfirst.dto.PostUpdateDTO;
import com.example.projectfirst.entity.Post;
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
    public List<PostResponseDTO> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPostsWithParams(userId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateDTO newPostDTO){
        return postService.createOnePost(newPostDTO);
    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateDTO updatePost){
        return postService.updateOnePostById(postId, updatePost);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePostById(postId);
    }


}
