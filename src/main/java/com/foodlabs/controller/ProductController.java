package com.foodlabs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodlabs.dto.request.product.*;
import com.foodlabs.dto.response.product.ProductImageResponse;
import com.foodlabs.dto.response.product.ProductOfferResponse;
import com.foodlabs.dto.response.product.ProductResponse;
import com.foodlabs.dto.response.product.ProductStatusResponse;
import com.foodlabs.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<ProductResponse> getProducts() {
        return service.getProducts();
    }

    @GetMapping("{productId}")
    public ProductResponse getProduct(@PathVariable final UUID productId) {
        return service.getProductById(productId);
    }

    @PutMapping("{productId}/image")
    public ProductImageResponse updateProductImage(@PathVariable final UUID productId,
                                                   @RequestBody @Valid final UpdateProductImageRequest request) {
        return service.updateProductImage(productId, request);
    }

    @PutMapping("{productId}/offer")
    public ProductOfferResponse updateProductOffer(@PathVariable final UUID productId,
                                                   @RequestBody @Valid final UpdateProductOfferRequest request) {
        return service.updateProductOffer(productId, request);
    }

    @PutMapping("{productId}/status")
    public ProductStatusResponse updateProductStatus(@PathVariable final UUID productId,
                                                     @RequestBody @Valid final UpdateProductStatusRequest request) {
        return service.updateProductStatus(productId, request);
    }

    @PutMapping("{productId}/details")
    public ProductResponse updateProductDetails(@PathVariable final UUID productId,
                                                @RequestBody @Valid final UpdateProductDetailsRequest request) {
        return service.updateProductDetails(productId, request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createNewProduct(@RequestBody @Valid final CreateProductRequest request) {
        return service.createNewProduct(request);
    }

    @DeleteMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable final UUID productId) {
        service.deleteProduct(productId);
    }

    @GetMapping("/catalog")
    public ResponseEntity<Object> getCatalog() {
        String filePath = Paths.get("catalog.json").toString();
        File file = new File(filePath);

        try {

            ObjectMapper objectMapper = new ObjectMapper();

            Object jsonData = objectMapper.readValue(file, Object.class);

            log.info(jsonData.toString());

            return new ResponseEntity<>(jsonData, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
