package com.example.app.dto;

import java.util.List;

public class FormSubmissionDto {
    private Long serviceId; // ID of the eService
    private List<FormFieldValueDto> formFields;

    // Getters and Setters
    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public List<FormFieldValueDto> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<FormFieldValueDto> formFields) {
        this.formFields = formFields;
    }

    public static class FormFieldValueDto {
        private String fieldName;
        private String value;

        // Getters and Setters
        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
