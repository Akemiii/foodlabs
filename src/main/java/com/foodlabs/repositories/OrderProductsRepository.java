package com.foodlabs.repositories;

import com.foodlabs.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductsRepository extends JpaRepository<OrderItem, Long> {
}
