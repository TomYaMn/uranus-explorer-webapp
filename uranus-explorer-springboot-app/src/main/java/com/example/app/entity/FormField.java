package com.example.app.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "form_field")
public class FormField extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "field_name", nullable = false)
    private String fieldName;

    @Column(name = "is_required", nullable = false)
    private boolean isRequired;

    @ManyToOne
    @JoinColumn(name = "e_service_id", nullable = false)
    private EService eService;

    @ManyToOne
    @JoinColumn(name = "field_type_id", referencedColumnName = "id", nullable = false)
    private FieldType fieldType;

    @Column(name = "field_options", columnDefinition = "JSON")
    private String fieldOptions;

    @OneToMany(mappedBy = "formField", cascade = CascadeType.ALL)
    private List<FormFieldValue> values;

    @OneToMany(mappedBy = "formField", cascade = CascadeType.ALL)
    private List<FormFieldDocument> documents;

    @OneToOne(mappedBy = "formField", cascade = CascadeType.ALL) // Add this line to associate with FormFieldTooltip
    private FormFieldTooltip tooltip; // This is the tooltip relationship

    // Getters and Setters
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

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldOptions() {
        return fieldOptions;
    }

    public void setFieldOptions(String fieldOptions) {
        this.fieldOptions = fieldOptions;
    }

    public List<FormFieldValue> getValues() {
        return values;
    }

    public void setValues(List<FormFieldValue> values) {
        this.values = values;
    }

    public List<FormFieldDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<FormFieldDocument> documents) {
        this.documents = documents;
    }

    public FormFieldTooltip getTooltip() {
        return tooltip; // Ensure the tooltip is accessible
    }

    public void setTooltip(FormFieldTooltip tooltip) {
        this.tooltip = tooltip;
    }
}
