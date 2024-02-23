package com.foodlabs.dto.response;
import lombok.*;

import java.util.UUID;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID userId;
    private String name;
    private String email;
    private boolean admin;
    private int ordersCount;
}
