package com.foodlabs.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private UUID categoryId;
    private String name;
    private String image;
    private String description;

}
