package com.foodlabs.controller;

import com.foodlabs.DTO.request.CreateCategoryRequest;
import com.foodlabs.DTO.request.UpdateCategoryRequest;
import com.foodlabs.DTO.response.CategoryResponse;
import com.foodlabs.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {

    public final CategoryService service;

    @GetMapping
    public List<CategoryResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("{categoryId}")
    public CategoryResponse getById(@PathVariable UUID categoryId){
        return service.getById(categoryId);
    }

    @PostMapping("")
    public CategoryResponse createCategory(@RequestBody @Valid final CreateCategoryRequest request){
        return service.createCategory(request);
    }

    @PutMapping("{categoryId}")
    public CategoryResponse updateCategory(@PathVariable UUID categoryId,
                                           @RequestBody @Valid final UpdateCategoryRequest request){
        return service.updateCategory(categoryId, request);
    }

    @DeleteMapping("{categoryId}")
    public void deleteById(@PathVariable UUID categoryId){
        service.deleteCategory(categoryId);
    }



}
