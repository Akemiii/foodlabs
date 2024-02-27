package com.foodlabs.service;

import com.foodlabs.dto.request.OrderRequest;
import com.foodlabs.dto.response.OrderResponse;
import com.foodlabs.factory.OrderFactory;
import com.foodlabs.model.Order;
import com.foodlabs.model.User;
import com.foodlabs.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;

    @Transactional
    public OrderResponse createOrder(final UUID userId,
                                     final OrderRequest request) {
        User user = userService.findById(userId);

        Order order = orderFactory.createOrderModel(request);

        order.setUser(user);

        orderRepository.save(order);

        return orderFactory.createOrderResponse(order);
    }

    public List<OrderResponse> getAllByUserId(final UUID userId) {
        final var orders = orderRepository.findAllByUser_userId(userId);

        return orders.stream().map(orderFactory::createOrderResponse).toList();
    }

    public OrderResponse getOrderById(final UUID orderId) {
        final var order = findById(orderId);

        return orderFactory.createOrderResponse(order);

    }

    public Order findById(final UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
    }

}
