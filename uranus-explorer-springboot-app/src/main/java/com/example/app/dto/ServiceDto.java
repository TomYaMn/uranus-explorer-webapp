package com.example.app.dto;

import java.util.List;

public class ServiceDto {

    private String category;
    private List<FormField> formFields;
    private String formTitle;

    public ServiceDto(String category, List<FormField> formFields, String formTitle) {
        this.category = category;
        this.formFields = formFields;
        this.formTitle = formTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<FormField> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<FormField> formFields) {
        this.formFields = formFields;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public static class FormField {
        private String fieldName;
        private String fieldType;
        private boolean isRequired;

        public FormField(String fieldName, String fieldType, boolean isRequired) {
            this.fieldName = fieldName;
            this.fieldType = fieldType;
            this.isRequired = isRequired;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldType() {
            return fieldType;
        }

        public void setFieldType(String fieldType) {
            this.fieldType = fieldType;
        }

        public boolean isRequired() {
            return isRequired;
        }

        public void setRequired(boolean required) {
            isRequired = required;
        }

// Getters and Setters
    }
}
