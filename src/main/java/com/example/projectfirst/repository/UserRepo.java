package com.example.projectfirst.repository;

import com.example.projectfirst.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
