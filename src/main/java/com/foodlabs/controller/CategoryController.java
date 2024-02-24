package com.foodlabs.controller;

import com.foodlabs.dto.request.CreateCategoryRequest;
import com.foodlabs.dto.request.UpdateCategoryRequest;
import com.foodlabs.dto.response.CategoryResponse;
import com.foodlabs.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    public final CategoryService service;

    @GetMapping
    public List<CategoryResponse> getProducts(){
        log.debug("CategoryController::getProducts started");
        return service.getProducts();
    }

    @GetMapping("{categoryId}")
    public CategoryResponse getProduct(@PathVariable UUID categoryId){
        log.debug("CategoryController::getProduct started");
        return service.getCategoryById(categoryId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createNewCategory(@RequestBody @Valid final CreateCategoryRequest request){
        log.debug("CategoryController::createNewCategory started");
        return service.createNewCategory(request);
    }

    @PutMapping("{categoryId}")
    public CategoryResponse updateCategory(@PathVariable UUID categoryId,
                                           @RequestBody @Valid final UpdateCategoryRequest request){
        log.debug("CategoryController::updateCategory started");
        return service.updateCategory(categoryId, request);
    }

    @DeleteMapping("{categoryId}")
    public void deleteCategory(@PathVariable UUID categoryId){
        log.debug("CategoryController::deleteCategory started");
        service.deleteCategory(categoryId);
    }



}
