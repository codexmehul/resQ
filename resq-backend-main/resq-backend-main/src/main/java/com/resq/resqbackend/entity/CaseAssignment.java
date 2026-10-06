package com.resq.resqbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "case_assignments")
@Data
public class CaseAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "ngo_id")
    private Ngo ngo;

    private LocalDateTime assignedAt = LocalDateTime.now();
    private String outcome;  // Feedback later
}