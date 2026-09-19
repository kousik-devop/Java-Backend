package com.example.SpringJdbcDemo.service;

import com.example.SpringJdbcDemo.entity.User;
import com.example.SpringJdbcDemo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE
    public User createUser(User user) {
        userRepository.createUser(user);
        return user;
    }

    // READ ALL
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    // READ ONE
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    // UPDATE
    public int updateUser(User user, Long id) {
        return userRepository.updateUser(user, id);
    }

    // DELETE
    public int deleteUser(Long id) {
        return userRepository.deleteUser(id);
    }
}