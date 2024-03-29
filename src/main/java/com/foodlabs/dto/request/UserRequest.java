package com.foodlabs.dto.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private boolean admin;
}
