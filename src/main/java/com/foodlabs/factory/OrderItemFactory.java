package com.foodlabs.factory;

import com.foodlabs.dto.request.OrderItemRequest;
import com.foodlabs.dto.response.OrderItemResponse;
import com.foodlabs.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemFactory {
    public OrderItem createOrderItemModel(final OrderItemRequest request){
        return OrderItem.builder()
                .product(request.getProduct())
                .quantity(request.getQuantity())
                .comment(request.getComment())
                .build();
    }

    public OrderItemResponse createOrderItemResponse(final OrderItem orderItem){
        return OrderItemResponse.builder()
                .product(orderItem.getProduct())
                .quantity(orderItem.getQuantity())
                .comment(orderItem.getComment())
                .build();
    }
}
