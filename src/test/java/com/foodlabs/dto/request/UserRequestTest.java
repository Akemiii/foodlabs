package com.foodlabs.dto.request;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRequestTest {

    @Test
    public void testGetterAndSetter() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "password123";
        boolean admin = true;

        UserRequest userRequest = new UserRequest();
        userRequest.setName(name);
        userRequest.setEmail(email);
        userRequest.setPassword(password);
        userRequest.setAdmin(admin);

        assertEquals(name, userRequest.getName());
        assertEquals(email, userRequest.getEmail());
        assertEquals(password, userRequest.getPassword());
        assertTrue(userRequest.isAdmin());
    }

    @Test
    public void testBuilder() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "password123";
        boolean admin = true;

        final var userRequest = UserRequest.builder()
                .name(name)
                .email(email)
                .password(password)
                .admin(admin).build();

        assertEquals(name, userRequest.getName());
        assertEquals(email, userRequest.getEmail());
        assertEquals(password, userRequest.getPassword());
        assertTrue(userRequest.isAdmin());
    }

    @Test
    public void testToString() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "password123";
        boolean admin = true;

        UserRequest userRequest = new UserRequest();
        userRequest.setName(name);
        userRequest.setEmail(email);
        userRequest.setPassword(password);
        userRequest.setAdmin(admin);

        String expectedToString = "UserRequest(name=John Doe, email=john.doe@example.com, password=password123, admin=true)";
        assertEquals(expectedToString, userRequest.toString());
    }
}