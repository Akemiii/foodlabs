package com.foodlabs.factory;

import com.foodlabs.DTO.request.CreateCategoryRequest;
import com.foodlabs.DTO.response.CategoryResponse;
import com.foodlabs.model.Category;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CategoryFactory {

    public Category createCategoryModel(CreateCategoryRequest request) {
        return Category.builder()
                .categoryId(UUID.randomUUID())
                .name(request.getName())
                .image(request.getImage())
                .description(request.getDescription())
                .build();
    }

    public CategoryResponse createCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .image(category.getImage())
                .description(category.getDescription())
                .build();
    }


}
