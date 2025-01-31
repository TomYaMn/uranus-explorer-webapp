package com.example.app.dto;

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
        private boolean isRequired;
        private String fieldTypeName; // Assuming FieldType is a simple name field
        private List<FormFieldOptionDto> options;
        private FormFieldTooltipDto tooltip;


        // Default constructor
        public FormField() {}

        // Parameterized constructor
        public FormField(String fieldName, String fieldTypeName, boolean required,
                         List<FormFieldOptionDto> options, FormFieldTooltipDto tooltip
                        ){
            this.fieldName = fieldName;
            this.fieldTypeName = fieldTypeName;
            this.isRequired = required;
            this.options = options;
            this.tooltip = tooltip;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public boolean isRequired() {
            return isRequired;
        }

        public void setRequired(boolean required) {
            isRequired = required;
        }

        public String getFieldTypeName() {
            return fieldTypeName;
        }

        public void setFieldTypeName(String fieldTypeName) {
            this.fieldTypeName = fieldTypeName;
        }

        public List<FormFieldOptionDto> getOptions() {
            return options;
        }

        public void setOptions(List<FormFieldOptionDto> options) {
            this.options = options;
        }

        public FormFieldTooltipDto getTooltip() {
            return tooltip;
        }

        public void setTooltip(FormFieldTooltipDto tooltip) {
            this.tooltip = tooltip;
        }
    }

    public static class FormFieldOptionDto {
        private String optionValue;

        public FormFieldOptionDto(String optionValue) {
            this.optionValue = optionValue;
        }

        public String getOptionValue() {
            return optionValue;
        }

        public void setOptionValue(String optionValue) {
            this.optionValue = optionValue;
        }
    }

    public static class FormFieldTooltipDto {
        private String tooltipText;

        public FormFieldTooltipDto(String tooltipText) {
            this.tooltipText = tooltipText;
        }

        public String getTooltipText() {
            return tooltipText;
        }

        public void setTooltipText(String tooltipText) {
            this.tooltipText = tooltipText;
        }
    }

    public static class FormFieldValueDto {
        private String userInputValue;

        public FormFieldValueDto(String userInputValue) {
            this.userInputValue = userInputValue;
        }

        public String getUserInputValue() {
            return userInputValue;
        }

        public void setUserInputValue(String userInputValue) {
            this.userInputValue = userInputValue;
        }
    }

    public static class FormFieldDocumentDto {
        private String documentUrl;
        private String documentType;

        public FormFieldDocumentDto(String documentUrl, String documentType) {
            this.documentUrl = documentUrl;
            this.documentType = documentType;
        }

        public String getDocumentUrl() {
            return documentUrl;
        }

        public void setDocumentUrl(String documentUrl) {
            this.documentUrl = documentUrl;
        }

        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
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
