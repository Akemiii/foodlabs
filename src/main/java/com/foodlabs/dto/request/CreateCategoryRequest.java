package com.foodlabs.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {

    @NotBlank(message = "category name shouldn't be NULL OR EMPTY")
    private String name;
    private String image;
    private String description;

}
