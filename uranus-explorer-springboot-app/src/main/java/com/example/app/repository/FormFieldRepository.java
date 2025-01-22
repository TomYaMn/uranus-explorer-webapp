package com.example.app.repository;

import com.example.app.entity.FormField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormFieldRepository extends JpaRepository<FormField, Long> {
}
