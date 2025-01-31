package com.example.app.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "field_type")
public class FieldType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "input_type_name", nullable = false, unique = true, length = 50)
    private String inputTypeName; // Mapped correctly to input_type_name in the table

    @OneToMany(mappedBy = "fieldType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FormField> formFields;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FormField> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<FormField> formFields) {
        this.formFields = formFields;
    }
}
