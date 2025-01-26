package com.example.app.dto;

import com.example.app.entity.FieldType;

import java.util.List;

public class EServiceDto {
    private String category;
    private List<FormField> formFields;
    private List<ServiceItemDto> serviceItems; // Use ServiceItemDto here

    public EServiceDto(String category, List<FormField> formFields, List<ServiceItemDto> serviceItems) {
        this.category = category;
        this.formFields = formFields;
        this.serviceItems = serviceItems;
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

    public List<ServiceItemDto> getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(List<ServiceItemDto> serviceItems) {
        this.serviceItems = serviceItems;
    }

    // FormField class implementation
    public static class FormField {
        private String fieldName;
        private String fieldType;
        private boolean required;

        // Constructor to map fields from the FormField entity
        public FormField(String fieldName, String fieldType, boolean required) {
            this.fieldName = fieldName;
            this.fieldType = fieldType;
            this.required = required;
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
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }
    }

    // Nested ServiceItemDto class
    public static class ServiceItemDto {
        private String item;

        public ServiceItemDto(String item) {
            this.item = item;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }
    }
}
