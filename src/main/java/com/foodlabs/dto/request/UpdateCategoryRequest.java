package com.foodlabs.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryRequest {

    private String name;
    private String image;
    private String description;
}
