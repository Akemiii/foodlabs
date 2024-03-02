package com.foodlabs.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String email;
    private String password;
    private boolean admin;
    private int ordersCount;

}
