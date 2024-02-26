package com.foodlabs.dto.response;

import com.foodlabs.model.Product;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderItemResponse {
    private Product product;
    private Integer quantity;
    private String comment;

}
