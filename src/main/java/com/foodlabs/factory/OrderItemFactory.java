package com.foodlabs.factory;

import com.foodlabs.dto.request.OrderItemRequest;
import com.foodlabs.dto.response.OrderItemResponse;
import com.foodlabs.model.OrderItem;
import com.foodlabs.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemFactory {

    private final ProductService productService;

    public OrderItem createOrderItemModel(final OrderItemRequest request) {
        return OrderItem.builder()
                .product(productService.findById(request.getProductId()))
                .quantity(request.getQuantity())
                .comment(request.getComment())
                .build();
    }

    public OrderItemResponse createOrderItemResponse(final OrderItem orderItem) {
        return OrderItemResponse.builder()
                .product(orderItem.getProduct())
                .quantity(orderItem.getQuantity())
                .comment(orderItem.getComment())
                .build();
    }
}
