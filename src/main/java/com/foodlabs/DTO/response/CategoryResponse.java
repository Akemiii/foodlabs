package com.foodlabs.DTO.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    public UUID categoryId;
    public String name;
    public String image;
    public String description;

}
