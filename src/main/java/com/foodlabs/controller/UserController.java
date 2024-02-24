package com.foodlabs.controller;

import com.foodlabs.dto.request.UserRequest;
import com.foodlabs.dto.response.UserResponse;
import com.foodlabs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {

        return service.createNewUser(request);
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable UUID userId) {

        return service.getUserById(userId);
    }

}
