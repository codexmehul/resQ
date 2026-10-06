package com.resq.resqbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String species;
    private String description;
    private double latitude;
    private double longitude;
    private LocalDateTime reportedAt = LocalDateTime.now();

    private String status = "PENDING";  // PENDING, ASSIGNED, RESOLVED
}