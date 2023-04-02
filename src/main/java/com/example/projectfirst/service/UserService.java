package com.example.projectfirst.service;

import com.example.projectfirst.entity.UserEntity;
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


    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public UserEntity saveOneUser(UserEntity newUser) {
        return userRepo.save(newUser);
    }

    public UserEntity getOneUser(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }

    public UserEntity updateOneUser(Long userId, UserEntity newUser) {
        Optional<UserEntity> userEntity = userRepo.findById(userId);
        if (userEntity.isPresent()){
            UserEntity foundUser = userEntity.get();
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
