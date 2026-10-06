package com.resq.resqbackend.controller;

import com.resq.resqbackend.dto.ReportRequest;
import com.resq.resqbackend.dto.ReportResponse;
import com.resq.resqbackend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportResponse> createReport(@RequestBody ReportRequest request) {
        return ResponseEntity.ok(reportService.createReport(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> getReport(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getReportById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReportResponse>> getUserReports(@PathVariable Long userId) {
        return ResponseEntity.ok(reportService.getUserReports(userId));
    }
}