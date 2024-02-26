package com.foodlabs.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class UpdateProductImageRequest {

    @NotBlank
    private String image;

}
