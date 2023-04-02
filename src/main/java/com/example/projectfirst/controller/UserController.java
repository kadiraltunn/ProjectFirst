package com.example.projectfirst.controller;

import com.example.projectfirst.entity.UserEntity;
import com.example.projectfirst.repository.UserRepo;
import com.example.projectfirst.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserRepo userRepo, UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity newUser){
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public UserEntity getOneUser(@PathVariable Long userId){
        return userService.getOneUser(userId);
    }

    @PutMapping("{userId}")
    public UserEntity updateOneUser(@PathVariable Long userId, @RequestBody UserEntity newUser){
        return userService.updateOneUser(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteOneUser(userId);
    }
}
