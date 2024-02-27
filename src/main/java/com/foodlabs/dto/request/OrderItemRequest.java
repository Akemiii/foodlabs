package com.foodlabs.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    @NotBlank
    private UUID productId;
    @NotBlank
    private Integer quantity;
    private String comment;
}
