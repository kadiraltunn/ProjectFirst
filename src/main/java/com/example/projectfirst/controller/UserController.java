package com.example.projectfirst.controller;

import com.example.projectfirst.entity.User;
import com.example.projectfirst.exception.UserNotFoundException;
import com.example.projectfirst.repository.UserRepo;
import com.example.projectfirst.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserRepo userRepo, UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.createOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        User user = userService.getOneUserById(userId);
        if (user == null)
            throw new UserNotFoundException();
        return userService.getOneUserById(userId);
    }

    @PutMapping("{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser){
        User user = userService.getOneUserById(userId);
        if (user == null)
            throw new UserNotFoundException();
        return userService.updateOneUserById(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        User user = userService.getOneUserById(userId);
        if (user == null)
            throw new UserNotFoundException();
        userService.deleteOneUser(userId);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleUserNotFound(){

    }
}
