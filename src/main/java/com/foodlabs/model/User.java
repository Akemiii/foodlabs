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
    public UUID userId;
    public String name;
    @Column(unique = true)
    public String email;
    public String password;
    public boolean admin;
    public int ordersCount;

}
