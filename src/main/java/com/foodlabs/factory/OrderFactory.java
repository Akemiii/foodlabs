package com.foodlabs.factory;

import com.foodlabs.dto.request.OrderRequest;
import com.foodlabs.dto.response.OrderResponse;
import com.foodlabs.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderFactory {
    final OrderItemFactory itemFactory;

    public Order createOrderModel(OrderRequest request) {
        return Order.builder()
                .orderId(UUID.randomUUID())
                .items(request.getItems().
                        stream()
                        .map(itemFactory::createOrderItemModel
                        ).toList())
                .build();
    }

    public OrderResponse createOrderResponse(Order order) {
        return OrderResponse.builder()
                .orderId(order.getOrderId())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .user(order.getUser())
                .items(order.getItems().stream()
                        .map(itemFactory::createOrderItemResponse)
                        .toList())
                .build();
    }

}
