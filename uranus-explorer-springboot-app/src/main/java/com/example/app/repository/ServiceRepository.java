package com.example.app.repository;

import com.example.app.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    ServiceEntity findByCategory(String category);
}