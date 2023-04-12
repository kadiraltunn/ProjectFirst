package com.example.projectfirst.controller;

import com.example.projectfirst.dto.LikeCreateDTO;
import com.example.projectfirst.dto.LikeResponseDTO;
import com.example.projectfirst.entity.Like;
import com.example.projectfirst.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponseDTO> getAllLikes(@RequestParam Optional<Long> userId,
                                             @RequestParam Optional<Long> postId){
        return likeService.getAllLikesWithParams(userId, postId);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLikeById(likeId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateDTO newLikeDTO){
        return likeService.createOneLike(newLikeDTO);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLikeById(likeId);
    }


}
