package com.foodlabs.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @Id
    private UUID productId;
    private String name;
    private BigDecimal price;
    private String image;
    private boolean offer;
    private BigDecimal offerPrice;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
