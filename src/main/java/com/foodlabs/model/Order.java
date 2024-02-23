package com.foodlabs.model;


import com.foodlabs.util.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private UUID orderId;
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
