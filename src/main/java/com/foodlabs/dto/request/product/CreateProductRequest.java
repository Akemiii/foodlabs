package com.foodlabs.dto.request.product;

import com.foodlabs.model.Category;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateProductRequest {

    private String name;
    private BigDecimal price;
    private String image;
    private boolean offer;
    private BigDecimal offerPrice;
    private Category category;
    private boolean active;
}
