package com.resq.resqbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ngos")
@Data
public class Ngo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;  // Hashed password

    private String name;
    private String specialization;  // e.g., "birds,mammals"
    private double latitude;
    private double longitude;
    private int currentWorkload = 0;
    private double performanceRating = 0.0;

    private String role = "NGO";
}