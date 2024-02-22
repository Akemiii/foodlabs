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
public class Product {

    @Id
    public UUID productId;
    public String name;
    public BigDecimal price;
    public String image;
    public boolean offer;
    public BigDecimal offerPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;


}
