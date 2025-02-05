package com.example.app.service;

import com.example.app.dto.SubmissionRequestDto;
import com.example.app.entity.*;
import com.example.app.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final EServiceRepository eServiceRepository;
    private final FormFieldRepository formFieldRepository;
    private final FormFieldValueRepository formFieldValueRepository;
    private final FormFieldDocumentRepository formFieldDocumentRepository;
    private final MinioService minioService;

    public SubmissionService(SubmissionRepository submissionRepository,
                             UserRepository userRepository,
                             EServiceRepository eServiceRepository, FormFieldRepository formFieldRepository,
                             FormFieldValueRepository formFieldValueRepository,
                             FormFieldDocumentRepository formFieldDocumentRepository,
                             MinioService minioService) {
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
        this.eServiceRepository = eServiceRepository;
        this.formFieldRepository = formFieldRepository;
        this.formFieldValueRepository = formFieldValueRepository;
        this.formFieldDocumentRepository = formFieldDocumentRepository;
        this.minioService = minioService;
    }

    @Transactional
    public Submission saveSubmission(SubmissionRequestDto dto) {
        // Fetch User entity
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        // Fetch EService entity
        EService eService = eServiceRepository.findById(dto.geteServiceId())
                .orElseThrow(() -> new RuntimeException("EService not found with id: " + dto.geteServiceId()));

        // Create and save Submission entity
        Submission submission = new Submission();
        submission.setUser(user);
        submission.seteService(eService);
        submission.setCommonFields(true, new Date());
        submission = submissionRepository.save(submission);

        // Save form field values
        for (SubmissionRequestDto.FormFieldValue fieldValue : dto.getFormFieldValues()) {
            FormField formField = formFieldRepository.findById(fieldValue.getFormFieldId())
                    .orElseThrow(() -> new RuntimeException("FormField not found with id: " + fieldValue.getFormFieldId()));

            FormFieldValue formFieldValue = new FormFieldValue();
            formFieldValue.setSubmission(submission);
            formFieldValue.setFormField(formField);
            formFieldValue.setUserInputValue(fieldValue.getUserInputValue());
            formFieldValue.setCommonFields(true, new Date());
            formFieldValueRepository.save(formFieldValue);
        }

        // Save form field documents
        if (dto.getFormFieldDocuments() != null) {
            for (SubmissionRequestDto.FormFieldDocument document : dto.getFormFieldDocuments()) {
                FormField formField = formFieldRepository.findById(document.getFormFieldId())
                        .orElseThrow(() -> new RuntimeException("FormField not found with id: " + document.getFormFieldId()));

                // Upload file to MinIO and get URL
                String documentUrl = minioService.uploadFile(document.getFormFieldId(), document.getFile());

                // Save document details in database
                FormFieldDocument formFieldDocument = new FormFieldDocument();
                formFieldDocument.setSubmission(submission);
                formFieldDocument.setFormField(formField);
                formFieldDocument.setDocumentUrl(documentUrl);
                formFieldDocument.setDocumentType(document.getDocumentType());
                formFieldDocument.setCommonFields(true, new Date());
                formFieldDocumentRepository.save(formFieldDocument);
            }
        }

        return submission;
    }
}
