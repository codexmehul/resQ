package com.resq.resqbackend.service;

import com.resq.resqbackend.dto.ReportRequest;
import com.resq.resqbackend.dto.ReportResponse;
import com.resq.resqbackend.entity.CaseAssignment;
import com.resq.resqbackend.entity.Ngo;
import com.resq.resqbackend.entity.Report;
import com.resq.resqbackend.entity.User;
import com.resq.resqbackend.repository.CaseAssignmentRepository;
import com.resq.resqbackend.repository.NgoRepository;
import com.resq.resqbackend.repository.ReportRepository;
import com.resq.resqbackend.repository.UserRepository;
import com.resq.resqbackend.util.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private NgoRepository ngoRepository;

    @Autowired
    private CaseAssignmentRepository caseAssignmentRepository;

    @Autowired
    private UserRepository userRepository;

    public ReportResponse createReport(ReportRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();  // Logged-in user's email
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Report report = new Report();
        report.setUser(user);
        report.setSpecies(request.getSpecies());
        report.setDescription(request.getDescription());
        report.setLatitude(request.getLatitude());
        report.setLongitude(request.getLongitude());
        report = reportRepository.save(report);

        // Simple assignment: Find nearest NGO
        Ngo nearestNgo = findNearestNgo(report.getLatitude(), report.getLongitude());
        if (nearestNgo != null) {
            CaseAssignment assignment = new CaseAssignment();
            assignment.setReport(report);
            assignment.setNgo(nearestNgo);
            caseAssignmentRepository.save(assignment);
            report.setStatus("ASSIGNED");
            reportRepository.save(report);
        }

        return mapToResponse(report, nearestNgo != null ? nearestNgo.getId() : null);
    }

    public ReportResponse getReportById(Long id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
        CaseAssignment assignment = caseAssignmentRepository.findByReportId(id).orElse(null);  // Custom query if needed
        Long assignedNgoId = assignment != null ? assignment.getNgo().getId() : null;
        return mapToResponse(report, assignedNgoId);
    }

    public List<ReportResponse> getUserReports(Long userId) {
        return reportRepository.findByUserId(userId).stream()
                .map(report -> {
                    CaseAssignment assignment = caseAssignmentRepository.findByReportId(report.getId()).orElse(null);
                    Long assignedNgoId = assignment != null ? assignment.getNgo().getId() : null;
                    return mapToResponse(report, assignedNgoId);
                })
                .collect(Collectors.toList());
    }

    public List<ReportResponse> getNgoAssignments() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();  // Logged-in NGO's email
        Ngo ngo = ngoRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("NGO not found"));

        return caseAssignmentRepository.findByNgoId(ngo.getId()).stream()
                .map(assignment -> mapToResponse(assignment.getReport(), ngo.getId()))
                .collect(Collectors.toList());
    }

    private Ngo findNearestNgo(double lat, double lon) {
        List<Ngo> ngos = ngoRepository.findAll();
        if (ngos.isEmpty()) return null;

        Ngo nearest = null;
        double minDistance = Double.MAX_VALUE;
        for (Ngo ngo : ngos) {
            double distance = DistanceUtil.calculateDistance(lat, lon, ngo.getLatitude(), ngo.getLongitude());
            if (distance < minDistance) {
                minDistance = distance;
                nearest = ngo;
            }
        }
        return nearest;
    }

    private ReportResponse mapToResponse(Report report, Long assignedNgoId) {
        ReportResponse response = new ReportResponse();
        response.setId(report.getId());
        response.setUserId(report.getUser().getId());
        response.setSpecies(report.getSpecies());
        response.setDescription(report.getDescription());
        response.setLatitude(report.getLatitude());
        response.setLongitude(report.getLongitude());
        response.setReportedAt(report.getReportedAt());
        response.setStatus(report.getStatus());
        response.setAssignedNgoId(assignedNgoId);
        return response;
    }
}