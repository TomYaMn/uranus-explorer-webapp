package com.example.app.service;

import com.example.app.dto.FormSubmissionDto;
import org.springframework.stereotype.Service;

@Service
public class FormSubmissionService {

    public void processFormSubmission(FormSubmissionDto formSubmissionDto) {
        // Logic to process the form submission
        System.out.println("Processing form for Service ID: " + formSubmissionDto.getServiceId());

        formSubmissionDto.getFormFields().forEach(field -> {
            System.out.println("Field: " + field.getFieldName() + " | Value: " + field.getValue());
        });

        // You can add database persistence logic here
    }
}
