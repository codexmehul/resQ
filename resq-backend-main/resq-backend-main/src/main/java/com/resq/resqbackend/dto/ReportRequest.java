package com.resq.resqbackend.dto;

import lombok.Data;

@Data
public class ReportRequest {
    private String species;
    private String description;
    private double latitude;
    private double longitude;
}