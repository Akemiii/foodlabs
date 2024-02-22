package com.foodlabs.model;


import com.foodlabs.Util.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import javax.swing.plaf.PanelUI;
import java.util.Dictionary;
import java.util.List;
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
    public UUID orderId;
    public OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

}
