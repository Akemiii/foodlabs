package com.foodlabs.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean admin;
    private int ordersCount;

}
