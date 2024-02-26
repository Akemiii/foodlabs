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

    /**
     * Creates a Category model object based on the provided request.
     *
     * @param request The request containing category details.
     * @return A Category model object created from the request.
     */
    public Category createCategoryModel(CreateCategoryRequest request) {
        log.debug("CategoryFactory::createCategoryModel started");
        return Category.builder()
                .categoryId(UUID.randomUUID())
                .name(request.getName())
                .image(request.getImage())
                .description(request.getDescription())
                .build();
    }

    /**
     * Creates a CategoryResponse object based on the provided Category model.
     *
     * @param category The Category model object to create the response from.
     * @return A CategoryResponse object created from the Category model.
     */
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
