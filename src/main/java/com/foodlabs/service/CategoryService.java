package com.foodlabs.service;

import com.foodlabs.DTO.request.CreateCategoryRequest;
import com.foodlabs.DTO.request.UpdateCategoryRequest;
import com.foodlabs.DTO.response.CategoryResponse;
import com.foodlabs.factory.CategoryFactory;
import com.foodlabs.model.Category;
import com.foodlabs.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {

    public final CategoryRepository repository;
    public final CategoryFactory factory;

    @SneakyThrows
    private Category findById(UUID categoryId) {
        return repository.findById(categoryId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public CategoryResponse getById(final UUID categoryId) {
        return factory.createCategoryResponse(findById(categoryId));
    }

    public List<CategoryResponse> getAll() {
        final var categories = repository.findAll();

        return categories.stream().map(factory::createCategoryResponse).toList();
    }

    public CategoryResponse createCategory(@RequestBody @Valid final CreateCategoryRequest request) {
        final var category = factory.createCategoryModel(request);

        repository.save(category);

        return factory.createCategoryResponse(category);
    }

    public CategoryResponse updateCategory(final UUID categoryId, @RequestBody @Valid final UpdateCategoryRequest request) {

        final var category = findById(categoryId);

        if (Objects.nonNull(request.getName()) && !request.getName().isEmpty())
            category.setName(request.getName());
        if (Objects.nonNull(request.getImage()) && !request.getImage().isEmpty())
            category.setImage(request.getImage());
        if (Objects.nonNull(request.getDescription()) && !request.getDescription().isEmpty())
            category.setDescription(request.getDescription());

        repository.save(category);

        return factory.createCategoryResponse(category);
    }

    public void deleteCategory(final UUID categoryId) {
        final var category = findById(categoryId);

        repository.delete(category);
    }

}
