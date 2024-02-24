package com.foodlabs.controller;

import com.foodlabs.dto.request.UserRequest;
import com.foodlabs.dto.response.UserResponse;
import com.foodlabs.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService service;

    @InjectMocks
    private UserController userController;

    @Test
    void testCreateUser() {

        UserRequest userRequest = new UserRequest();
        UserResponse expectedResponse = new UserResponse();

        when(service.createNewUser(userRequest)).thenReturn(expectedResponse);

        UserResponse result = userController.createUser(userRequest);

        assertEquals(expectedResponse, result);
    }

    @Test
    void testGetUser() {
        UUID userId = UUID.randomUUID();
        UserResponse expectedResponse = new UserResponse();

        when(service.getUserById(userId)).thenReturn(expectedResponse);

        UserResponse result = userController.getUser(userId);

        assertEquals(expectedResponse, result);
    }
}