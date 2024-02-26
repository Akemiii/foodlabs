package com.foodlabs.dto.response.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOfferResponse {
    private boolean offer;
    private BigDecimal offerPrice;
}
