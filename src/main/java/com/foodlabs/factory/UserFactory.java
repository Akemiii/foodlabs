package com.foodlabs.factory;

import com.foodlabs.dto.request.UserRequest;
import com.foodlabs.dto.response.UserResponse;
import com.foodlabs.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserFactory {

    /**
     * Creates a User object based on the provided UserRequest.
     * @param request The UserRequest containing details for creating the user.
     * @return The User object created from the UserRequest.
     */
    public User CreateUserModel(UserRequest request){
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .admin(request.isAdmin())
                .build();
    }

    /**
     * Creates a UserResponse object based on the provided User object.
     * @param user The User object to create the response from.
     * @return The UserResponse object created from the User object.
     */
    public UserResponse CreateUserResponse(User user){
        return UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .admin(user.isAdmin())
                .build();
    }

}
