package com.foodlabs.dto.request;

import com.foodlabs.model.Product;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    private Product product;
    private Integer quantity;
    private String comment;
}
