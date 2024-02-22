package com.foodlabs.repositories;

import com.foodlabs.model.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {
}
