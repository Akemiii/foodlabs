package com.foodlabs.dto.response;

import com.foodlabs.model.User;
import com.foodlabs.util.OrderStatus;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private UUID orderId;
    private User user;
    private OrderStatus status;
    private List<OrderItemResponse> items;
    private Timestamp createdAt;
}
