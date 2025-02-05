package com.example.app.repository;

import com.example.app.entity.FormFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormFieldValueRepository extends JpaRepository<FormFieldValue, Long> {
}