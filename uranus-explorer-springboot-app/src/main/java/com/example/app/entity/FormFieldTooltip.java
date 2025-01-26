package com.example.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "form_field_tooltips")
public class FormFieldTooltip extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "form_field_id", nullable = false)
    private FormField formField;

    @Column(name = "tooltip_text", nullable = false, columnDefinition = "TEXT")
    private String tooltipText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FormField getFormField() {
        return formField;
    }

    public void setFormField(FormField formField) {
        this.formField = formField;
    }

    public String getTooltipText() {
        return tooltipText;
    }

    public void setTooltipText(String tooltipText) {
        this.tooltipText = tooltipText;
    }
}
