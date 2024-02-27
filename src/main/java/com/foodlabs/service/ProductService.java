package com.foodlabs.service;

import com.foodlabs.dto.request.product.*;
import com.foodlabs.dto.response.product.ProductImageResponse;
import com.foodlabs.dto.response.product.ProductOfferResponse;
import com.foodlabs.dto.response.product.ProductResponse;
import com.foodlabs.dto.response.product.ProductStatusResponse;
import com.foodlabs.factory.ProductFactory;
import com.foodlabs.model.Product;
import com.foodlabs.repositories.ProductRepository;
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
public class ProductService {
    private final ProductRepository repository;
    private final ProductFactory factory;
    private final CatalogService catalogService;

    public Product findById(final UUID productId) {
        log.info("ProductService::findById {}", productId);

        return repository.findById(productId).orElseThrow(EntityNotFoundException::new);
    }

    public ProductResponse getProductById(final UUID productId) {
        return factory.createProductResponse(findById(productId));
    }

    public List<ProductResponse> getProducts() {
        log.info("ProductService::getProducts started");

        final var products = repository.findAll();
        log.info("ProductService::getProducts return all products");
        return products.stream().map(factory::createProductResponse).toList();
    }

    public ProductResponse createNewProduct(@RequestBody @Valid final CreateProductRequest request) {
        log.info("ProductService::createNewProduct started");

        final var product = factory.createProductModel(request);
        log.info("ProductService::createNewProduct mapped {}", product);

        saveProduct(product);

        return factory.createProductResponse(product);
    }

    public ProductImageResponse updateProductImage(final UUID productId,
                                                   @RequestBody @Valid final UpdateProductImageRequest request) {
        final var product = findById(productId);

        product.setImage(request.getImage());

        saveProduct(product);

        return factory.createProductImageResponse(product);
    }

    public ProductStatusResponse updateProductStatus(final UUID productId,
                                                     @RequestBody @Valid final UpdateProductStatusRequest request) {
        final var product = findById(productId);

        product.setActive(request.isActive());

        saveProduct(product);

        return factory.createProductStatusResponse(product);
    }

    public ProductOfferResponse updateProductOffer(final UUID productId,
                                                   @RequestBody @Valid final UpdateProductOfferRequest request) {
        final var product = findById(productId);

        product.setOffer(request.isOffer());
        if (Objects.nonNull(request.getOfferPrice()))
            product.setOfferPrice(request.getOfferPrice());
        saveProduct(product);

        return factory.createProductOfferResponse(product);
    }

    private void saveProduct(Product product) {
        repository.save(product);
        log.info("Product saved : {}", product);
        catalogService.send(product.toString());
    }

    public ProductResponse updateProductDetails(final UUID productId,
                                                @RequestBody @Valid final UpdateProductDetailsRequest request) {
        final var product = findById(productId);

        if (isNotBlank(request.getName()))
            product.setName(request.getName());
        if (Objects.nonNull(request.getPrice()))
            product.setPrice(request.getPrice());
        if (isNotBlank(request.getImage()))
            product.setImage(request.getImage());

        product.setActive(request.isActive());
        product.setOffer(request.isOffer());
        if (Objects.nonNull(request.getOfferPrice()))
            product.setOfferPrice(request.getOfferPrice());
        if (Objects.nonNull(request.getCategory()))
            product.setCategory(request.getCategory());

        saveProduct(product);

        return factory.createProductResponse(product);
    }

    public void deleteProduct(final UUID productId) {
        final var product = findById(productId);

        repository.delete(product);
    }

    private boolean isNotBlank(String value) {
        return Objects.nonNull(value) && !value.isEmpty();
    }

}
