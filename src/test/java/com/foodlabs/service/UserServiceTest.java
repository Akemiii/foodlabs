package com.foodlabs.service;

import com.foodlabs.dto.request.UserRequest;
import com.foodlabs.dto.response.UserResponse;
import com.foodlabs.factory.UserFactory;
import com.foodlabs.model.User;
import com.foodlabs.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserFactory factory;

    @InjectMocks
    @Autowired
    private UserService userService;

    @Test
    void testGetUserById_Success() {
        // Given
        UUID userId = UUID.randomUUID();
        User user = new User(); // create a user object as per your implementation
        UserResponse userResponse = new UserResponse(); // create a user response object as per your implementation

        when(repository.findById(userId)).thenReturn(Optional.of(user));
        when(factory.createUserResponse(user)).thenReturn(userResponse);

        // When
        UserResponse result = userService.getUserById(userId);

        // Then
        assertNotNull(result);
        assertEquals(userResponse, result);
        verify(repository, times(1)).findById(userId);
        verify(factory, times(1)).createUserResponse(user);
    }

    @Test
    void testGetUserById_EntityNotFoundException() {
        // Given
        UUID userId = UUID.randomUUID();

        when(repository.findById(userId)).thenReturn(Optional.empty());

        // When
        assertThrows(EntityNotFoundException.class, () -> userService.getUserById(userId));

        // Then
        verify(repository, times(1)).findById(userId);
        verify(factory, never()).createUserResponse(any());
    }

    @Test
    void testCreateNewUser_Success() {
        // Given
        UserRequest userRequest = new UserRequest(); // create a user request object as per your implementation
        User user = new User(); // create a user object as per your implementation
        UserResponse userResponse = new UserResponse(); // create a user response object as per your implementation

        when(factory.createUserModel(userRequest)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(factory.createUserResponse(user)).thenReturn(userResponse);

        // When
        UserResponse result = userService.createNewUser(userRequest);

        // Then
        assertNotNull(result);
        assertEquals(userResponse, result);
        verify(factory, times(1)).createUserModel(userRequest);
        verify(repository, times(1)).save(user);
        verify(factory, times(1)).createUserResponse(user);
    }

}