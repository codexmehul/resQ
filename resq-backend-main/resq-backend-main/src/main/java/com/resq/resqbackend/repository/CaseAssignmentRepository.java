package com.resq.resqbackend.repository;

import com.resq.resqbackend.entity.CaseAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CaseAssignmentRepository extends JpaRepository<CaseAssignment, Long> {
    List<CaseAssignment> findByNgoId(Long ngoId);
    Optional<CaseAssignment> findByReportId(Long reportId);
}