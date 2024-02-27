package com.foodlabs.repositories;

import com.foodlabs.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    public List<Order> findAllByUser_userId(UUID userId);
}
