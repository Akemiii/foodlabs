package com.foodlabs.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "orderProducts")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class orderProducts {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long orderProductsId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    public Order orderId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product productId;
    private Integer quantity;
    private String comment;

}
