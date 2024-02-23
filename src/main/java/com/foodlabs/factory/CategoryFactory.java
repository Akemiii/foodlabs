package com.foodlabs.factory;

import com.foodlabs.dto.request.CreateCategoryRequest;
import com.foodlabs.dto.response.CategoryResponse;
import com.foodlabs.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CategoryFactory {

    public Category createCategoryModel(CreateCategoryRequest request) {
        log.debug("CategoryFactory::createCategoryModel started");
        return Category.builder()
                .categoryId(UUID.randomUUID())
                .name(request.getName())
                .image(request.getImage())
                .description(request.getDescription())
                .build();
    }

    public CategoryResponse createCategoryResponse(Category category) {
        log.debug("CategoryFactory::createCategoryResponse started");
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .image(category.getImage())
                .description(category.getDescription())
                .build();
    }


}
