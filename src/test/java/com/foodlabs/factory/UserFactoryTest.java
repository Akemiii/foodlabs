package com.foodlabs.factory;

import com.foodlabs.dto.request.UserRequest;
import com.foodlabs.dto.response.UserResponse;
import com.foodlabs.model.User;
import org.junit.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserFactoryTest {

    @Test
    public void testCreateUserModel() {
        UserRequest request = new UserRequest("John Doe", "john@example.com", "password123", true);
        UserFactory factory = new UserFactory();

        User user = factory.createUserModel(request);

        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertTrue(user.isAdmin());
    }

    @Test
    public void testCreateUserResponse() {
        User user = User.builder()
                .userId(UUID.randomUUID())
                .name("John Doe")
                .email("john@example.com")
                .admin(true)
                .build();
        UserFactory factory = new UserFactory();

        UserResponse userResponse = factory.createUserResponse(user);

        assertEquals(user.getUserId(), userResponse.getUserId());
        assertEquals(user.getName(), userResponse.getName());
        assertEquals(user.getEmail(), userResponse.getEmail());
        assertEquals(user.isAdmin(), userResponse.isAdmin());
    }

}