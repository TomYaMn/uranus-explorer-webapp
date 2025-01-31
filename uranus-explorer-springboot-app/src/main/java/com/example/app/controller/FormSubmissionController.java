package com.example.app.controller;

import com.example.app.dto.FormSubmissionDto;
import com.example.app.service.FormSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forms")
public class FormSubmissionController {

    private final FormSubmissionService formSubmissionService;

    public FormSubmissionController(FormSubmissionService formSubmissionService) {
        this.formSubmissionService = formSubmissionService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitForm(@RequestBody FormSubmissionDto formSubmissionDto) {
        formSubmissionService.processFormSubmission(formSubmissionDto);
        return ResponseEntity.ok("Form submitted successfully!");
    }
}
