package com.example.projectfirst.service;

import com.example.projectfirst.entity.User;
import com.example.projectfirst.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User createOneUser(User newUser) {
        return userRepo.save(newUser);
    }

    public User getOneUserById(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }

    public User updateOneUserById(Long userId, User newUser) {
        Optional<User> userEntity = userRepo.findById(userId);
        if (userEntity.isPresent()){
            User foundUser = userEntity.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepo.save(foundUser);
            return foundUser;
        }
        else
            return null;
    }

    public void deleteOneUser(Long userId) {
        userRepo.deleteById(userId);
    }
}
