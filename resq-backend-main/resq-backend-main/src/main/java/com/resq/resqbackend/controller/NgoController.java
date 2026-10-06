package com.resq.resqbackend.controller;

import com.resq.resqbackend.dto.ReportResponse;
import com.resq.resqbackend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ngos")
public class NgoController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/assignments")
    public ResponseEntity<List<ReportResponse>> getAssignments() {
        return ResponseEntity.ok(reportService.getNgoAssignments());
    }
}