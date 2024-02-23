package com.foodlabs.controller;

import com.foodlabs.dto.request.UserRequest;
import com.foodlabs.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {

        return null;
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable UUID userId) {

        return null;
    }

    @GetMapping
    public List<UserResponse> getUsers(){

        return null;
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(){

        return null;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(){

    }

}
