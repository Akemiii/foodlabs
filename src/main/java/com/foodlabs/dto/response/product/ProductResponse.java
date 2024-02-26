package com.foodlabs.dto.response.product;

import com.foodlabs.model.Category;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private UUID productId;
    private String name;
    private BigDecimal price;
    private String image;
    private boolean offer;
    private BigDecimal offerPrice;
    private boolean active;
    private Category category;

}
