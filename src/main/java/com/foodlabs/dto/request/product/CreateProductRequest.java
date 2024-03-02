package com.foodlabs.dto.request.product;

import com.foodlabs.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateProductRequest {

    @NotBlank
    private String name;
    @NotNull
    private BigDecimal price;
    private String image;
    private boolean offer;
    private BigDecimal offerPrice;
    private Category category;
    private boolean active;
}
