package com.foodlabs.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductStatusRequest {

    @NotBlank
    private boolean active;
}
