package com.foodlabs.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "OrderItems")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long orderItemId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
    private String comment;

}
