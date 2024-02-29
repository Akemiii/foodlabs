package com.foodlabs.factory;

import com.foodlabs.dto.request.product.CreateProductRequest;
import com.foodlabs.dto.response.product.*;
import com.foodlabs.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ProductFactory {

    public Product createProductModel(CreateProductRequest request) {
        return Product.builder()
                .productId(UUID.randomUUID())
                .name(request.getName())
                .price(request.getPrice())
                .image(request.getImage())
                .offer(request.isOffer())
                .offerPrice(request.getOfferPrice())
                .active(request.isActive())
                .category(request.getCategory())
                .build();
    }

    public ProductResponse createProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .price(product.getPrice())
                .image(product.getImage())
                .offer(product.isOffer())
                .offerPrice(product.getOfferPrice())
                .active(product.isActive())
                .category(product.getCategory())
                .build();
    }

    public ProductCatalogResponse createProductCatalogResponse(Product product){
        return ProductCatalogResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .price(product.getPrice())
                .image(product.getImage())
                .offer(product.isOffer())
                .offerPrice(product.getOfferPrice())
                .active(product.isActive())
                .build();
    }

    public ProductImageResponse createProductImageResponse(Product product) {
        return ProductImageResponse
                .builder()
                .image(product.getImage())
                .build();
    }

    public ProductOfferResponse createProductOfferResponse(Product product) {
        return ProductOfferResponse.builder()
                .offer(product.isOffer())
                .offerPrice(product.getOfferPrice())
                .build();
    }

    public ProductStatusResponse createProductStatusResponse(Product product) {
        return ProductStatusResponse.builder()
                .active(product.isActive())
                .build();
    }

}
