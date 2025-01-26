package com.example.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "form_field")
public class FormField extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fieldName;
    private boolean isRequired;

    @ManyToOne
    @JoinColumn(name = "e_service_id", nullable = false)
    private EService eService;

    @ManyToOne
    @JoinColumn(name = "field_type_id", referencedColumnName = "id")
    private FieldType fieldTypes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EService geteService() {
        return eService;
    }

    public void seteService(EService eService) {
        this.eService = eService;
    }

    public FieldType getFieldTypes() {
        return fieldTypes;
    }

    public void setFieldTypes(FieldType fieldTypes) {
        this.fieldTypes = fieldTypes;
    }
}
