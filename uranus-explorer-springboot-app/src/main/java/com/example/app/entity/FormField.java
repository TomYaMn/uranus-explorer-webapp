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
    @JoinColumn(name = "field_type_id", referencedColumnName = "id")
    private FieldType fieldTypes;

    @OneToMany(mappedBy = "formField", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormFieldOption> options;

    @OneToOne(mappedBy = "formField", cascade = CascadeType.ALL, orphanRemoval = true)
    private FormFieldTooltip tooltips;

    @OneToOne(mappedBy = "formField", cascade = CascadeType.ALL, orphanRemoval = true)
    private FormFieldValue values;

    @OneToMany(mappedBy = "formField", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormFieldDocument> documents;

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

    public List<FormFieldOption> getOptions() {
        return options;
    }

    public void setOptions(List<FormFieldOption> options) {
        this.options = options;
    }

    public FormFieldTooltip getTooltips() {
        return tooltips;
    }

    public void setTooltips(FormFieldTooltip tooltips) {
        this.tooltips = tooltips;
    }

    public FormFieldValue getValues() {
        return values;
    }

    public void setValues(FormFieldValue values) {
        this.values = values;
    }

    public List<FormFieldDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<FormFieldDocument> documents) {
        this.documents = documents;
    }
}
