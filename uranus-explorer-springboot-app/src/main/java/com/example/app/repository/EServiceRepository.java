package com.example.app.repository;

import com.example.app.entity.EService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EServiceRepository extends JpaRepository<EService, Long> {
    EService findByCategory(String category);
}
