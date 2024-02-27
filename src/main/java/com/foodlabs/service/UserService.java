package com.foodlabs.service;

import com.foodlabs.dto.request.UserRequest;
import com.foodlabs.dto.response.UserResponse;
import com.foodlabs.factory.UserFactory;
import com.foodlabs.model.User;
import com.foodlabs.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    public final UserRepository repository;
    public final UserFactory factory;

    /**
     * Find a user by its ID.
     *
     * @param userId The ID of the user to find.
     * @return The found user.
     * @throws EntityNotFoundException if the user with the given ID does not exist.
     */
    public User findById(UUID userId) {
        log.debug("UserService::findById {}", userId);

        return repository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Retrieve a user by its ID and transform it into a UserResponse object.
     *
     * @param userId The ID of the user to retrieve.
     * @return The UserResponse object representing the retrieved user.
     */
    public UserResponse getUserById(final UUID userId) {
        return factory.createUserResponse(findById(userId));
    }

    /**
     * Creates a new user based on the provided request and returns the corresponding UserResponse.
     *
     * @param request The request containing details for creating the user.
     * @return The UserResponse representing the newly created user.
     */
    public UserResponse createNewUser(@RequestBody @Valid final UserRequest request) {
        log.debug("UserResponse::createNewUser started");

        final var user = factory.createUserModel(request);
        log.debug("UserResponse::createNewUser mapped {}", user);

        log.debug("UserResponse::createNewUser saving user {}", user);
        repository.save(user);
        log.debug("UserResponse::createNewUser saved user {}", user);

        return factory.createUserResponse(user);
    }
}
