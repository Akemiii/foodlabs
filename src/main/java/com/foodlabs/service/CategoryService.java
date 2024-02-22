package com.foodlabs.service;

import com.foodlabs.model.Category;
import com.foodlabs.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {

    public final CategoryRepository repository;

    @SneakyThrows
    public Category findById(UUID categoryId) {
        return repository.findById(categoryId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

    }

    public CategoryResponse getById(UUID categoryId){

        return null;
    }

    public CategoryRespose createCategory(final CreateCategoryRequest request){

        return null;
    }

    public CategoryResponse updateCategory(final UpdateCategoryRequest request){

        return null;
    }

    public void deleteCategory(UUID categoryId){

    }

}
