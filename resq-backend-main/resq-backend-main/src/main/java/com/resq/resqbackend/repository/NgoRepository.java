package com.resq.resqbackend.repository;

import com.resq.resqbackend.entity.Ngo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NgoRepository extends JpaRepository<Ngo, Long> {
    Optional<Ngo> findByEmail(String email);
}