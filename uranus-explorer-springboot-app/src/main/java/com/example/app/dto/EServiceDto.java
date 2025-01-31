package com.example.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EServiceDto {
    @JsonProperty("eServiceName")
    private String eServiceName; // eServiceName as per the schema
    private Long serviceId;
    private List<FormField> formFields;
    private List<ServiceItemDto> serviceItems;

    public EServiceDto(String eServiceName, Long serviceId, List<FormField> formFields, List<ServiceItemDto> serviceItems) {
        this.eServiceName = eServiceName;
        this.serviceId = serviceId;
        this.formFields = formFields;
        this.serviceItems = serviceItems;
    }

    public String getEServiceName() {
        return eServiceName;
    }

    public void setEServiceName(String eServiceName) {
        this.eServiceName = eServiceName;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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

    // Updated FormField class with field_type_id and e_service_id
    public static class FormField {
        private String fieldName;
        private boolean isRequired;
        private Long fieldTypeId; // Changed to align with the entity's field type ID
        private FormFieldTooltipDto tooltip;
        private Long eServiceId; // Changed to eServiceId for the reference
        private Boolean isActive; // Added isActive to match entity
        private String fieldOptions; // Store field options as JSON string

        public FormField(String fieldName, boolean isRequired, Long fieldTypeId, FormFieldTooltipDto tooltip, Long eServiceId, Boolean isActive, String fieldOptions) {
            this.fieldName = fieldName;
            this.isRequired = isRequired;
            this.fieldTypeId = fieldTypeId;
            this.tooltip = tooltip;
            this.eServiceId = eServiceId;
            this.isActive = isActive;
            this.fieldOptions = fieldOptions;
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

        public Long getFieldTypeId() {
            return fieldTypeId;
        }

        public void setFieldTypeId(Long fieldTypeId) {
            this.fieldTypeId = fieldTypeId;
        }

        public FormFieldTooltipDto getTooltip() {
            return tooltip;
        }

        public void setTooltip(FormFieldTooltipDto tooltip) {
            this.tooltip = tooltip;
        }

        public Long geteServiceId() {
            return eServiceId;
        }

        public void seteServiceId(Long eServiceId) {
            this.eServiceId = eServiceId;
        }

        public Boolean getActive() {
            return isActive;
        }

        public void setActive(Boolean active) {
            isActive = active;
        }

        public String getFieldOptions() {
            return fieldOptions;
        }

        public void setFieldOptions(String fieldOptions) {
            this.fieldOptions = fieldOptions;
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
