package com.example.app.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class SubmissionRequestDto {
    private Long userId;
    private Long eServiceId;
    private List<FormFieldValue> formFieldValues;
    private List<FormFieldDocument> formFieldDocuments;

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long geteServiceId() { return eServiceId; }
    public void seteServiceId(Long eServiceId) { this.eServiceId = eServiceId; }

    public List<FormFieldValue> getFormFieldValues() { return formFieldValues; }
    public void setFormFieldValues(List<FormFieldValue> formFieldValues) { this.formFieldValues = formFieldValues; }

    public List<FormFieldDocument> getFormFieldDocuments() { return formFieldDocuments; }
    public void setFormFieldDocuments(List<FormFieldDocument> formFieldDocuments) { this.formFieldDocuments = formFieldDocuments; }

    public static class FormFieldValue {
        private Long formFieldId;
        private String userInputValue; // Store as JSON String if needed

        // Getters and Setters
        public Long getFormFieldId() { return formFieldId; }
        public void setFormFieldId(Long formFieldId) { this.formFieldId = formFieldId; }

        public String getUserInputValue() { return userInputValue; }
        public void setUserInputValue(String userInputValue) { this.userInputValue = userInputValue; }
    }

    public static class FormFieldDocument {
        private Long formFieldId;
        private MultipartFile file; // Instead of documentUrl
        private String documentType; // pdf, jpg, png, docx

        // Getters and Setters
        public Long getFormFieldId() { return formFieldId; }
        public void setFormFieldId(Long formFieldId) { this.formFieldId = formFieldId; }

        public MultipartFile getFile() { return file; }
        public void setFile(MultipartFile file) { this.file = file; }

        public String getDocumentType() { return documentType; }
        public void setDocumentType(String documentType) { this.documentType = documentType; }
    }
}
