package com.foodlabs.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orderProducts")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderProducts {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long orderProductsId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;
    private Integer quantity;
    private String comment;

}
