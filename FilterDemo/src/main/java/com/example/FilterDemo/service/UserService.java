package com.example.FilterDemo.service;

import com.example.FilterDemo.dto.UserRequestDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void createUser(UserRequestDto user){
        System.out.println("User Created");
    }
}
