package com.foodlabs.model;

import org.junit.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    public void testGetterAndSetter() {
        UUID userId = UUID.randomUUID();
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "password123";
        boolean admin = true;
        int ordersCount = 5;

        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setAdmin(admin);
        user.setOrdersCount(ordersCount);

        assertEquals(userId, user.getUserId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertTrue(user.isAdmin());
        assertEquals(ordersCount, user.getOrdersCount());
    }

    @Test
    public void testBuilder() {
        UUID userId = UUID.randomUUID();
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "password123";
        boolean admin = true;
        int ordersCount = 5;

        final var user = User.builder()
                .userId(userId)
                .name(name)
                .email(email)
                .password(password)
                .admin(admin)
                .ordersCount(ordersCount)
                .build();

        assertEquals(userId, user.getUserId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertTrue(user.isAdmin());
        assertEquals(ordersCount, user.getOrdersCount());

    }
}