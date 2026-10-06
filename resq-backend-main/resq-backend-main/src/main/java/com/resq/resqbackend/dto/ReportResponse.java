package com.resq.resqbackend.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReportResponse {
    private Long id;
    private Long userId;
    private String species;
    private String description;
    private double latitude;
    private double longitude;
    private LocalDateTime reportedAt;
    private String status;
    private Long assignedNgoId;  // If assigned
}