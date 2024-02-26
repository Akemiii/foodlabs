package com.foodlabs.service;

import com.foodlabs.dto.request.CreateCategoryRequest;
import com.foodlabs.dto.request.UpdateCategoryRequest;
import com.foodlabs.dto.response.CategoryResponse;
import com.foodlabs.factory.CategoryFactory;
import com.foodlabs.model.Category;
import com.foodlabs.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryFactory factory;

    /**
     * Find a category by its ID.
     *
     * @param categoryId The ID of the category to find.
     * @return The found category.
     * @throws EntityNotFoundException if the category with the given ID does not exist.
     */
    private Category findById(final UUID categoryId) {
        log.info("CategoryService::findById {}", categoryId);

        return repository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Retrieve a category by its ID and transform it into a CategoryResponse object.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return The CategoryResponse object representing the retrieved category.
     */
    public CategoryResponse getCategoryById(final UUID categoryId) {
        return factory.createCategoryResponse(findById(categoryId));
    }

    /**
     * Retrieves all categories and transforms them into a list of CategoryResponse objects.
     *
     * @return List of CategoryResponse objects representing the retrieved categories.
     */
    public List<CategoryResponse> getCategories() {
        log.info("CategoryService::getCategories started");

        final var categories = repository.findAll();
        log.info("CategoryService::getCategories {}", categories);
        return categories.stream().map(factory::createCategoryResponse).toList();
    }

    /**
     * Creates a new category based on the provided request and returns the corresponding CategoryResponse.
     *
     * @param request The request containing details for creating the category.
     * @return The CategoryResponse representing the newly created category.
     */
    public CategoryResponse createNewCategory(@RequestBody @Valid final CreateCategoryRequest request) {
        log.info("CategoryService::createNewCategory started");

        final var category = factory.createCategoryModel(request);
        log.info("CategoryService::createNewCategory mapped {}", category);

        log.info("CategoryService::createNewCategory saving category {}", category);
        repository.save(category);
        log.info("CategoryService::createNewCategory saved category {}", category);

        return factory.createCategoryResponse(category);
    }

    /**
     * Updates an existing category with the provided ID using the details from the request.
     *
     * @param categoryId The ID of the category to update.
     * @param request    The request containing updated details for the category.
     * @return The CategoryResponse representing the updated category.
     */
    public CategoryResponse updateCategory(final UUID categoryId,
                                           @RequestBody @Valid final UpdateCategoryRequest request) {
        log.info("CategoryService::updateCategory started");

        final var category = findById(categoryId);

        log.info("CategoryService::updateCategory setter nonNull fields");
        if (Objects.nonNull(request.getName()) && !request.getName().isEmpty())
            category.setName(request.getName());
        if (Objects.nonNull(request.getImage()) && !request.getImage().isEmpty())
            category.setImage(request.getImage());
        if (Objects.nonNull(request.getDescription()) && !request.getDescription().isEmpty())
            category.setDescription(request.getDescription());

        log.info("CategoryService::updateCategory updating category {}", category);
        repository.save(category);
        log.info("CategoryService::updateCategory updated category {}", category);

        return factory.createCategoryResponse(category);
    }

    /**
     * Deletes the category with the provided ID.
     *
     * @param categoryId The ID of the category to delete.
     */
    public void deleteCategory(final UUID categoryId) {
        log.info("CategoryService::deleteCategory started");

        final var category = findById(categoryId);

        log.info("CategoryService::updateCategory deleting category {}", category);
        repository.delete(category);
        log.info("CategoryService::updateCategory deleted category {}", category);
    }

}
