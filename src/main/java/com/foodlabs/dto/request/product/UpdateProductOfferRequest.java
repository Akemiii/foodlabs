package com.foodlabs.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductOfferRequest {
    @NotBlank
    private boolean offer;
    private BigDecimal offerPrice;
}
