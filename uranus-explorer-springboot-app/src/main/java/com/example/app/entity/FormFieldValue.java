package com.example.app.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "form_field_value")
public class FormFieldValue extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission; // Referencing submission table

    @ManyToOne
    @JoinColumn(name = "form_field_id", nullable = false)
    private FormField formField; // Referencing form_field table

    @Column(name = "user_input_value", columnDefinition = "JSON")
    private String userInputValue; // Store as JSON

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public FormField getFormField() {
        return formField;
    }

    public void setFormField(FormField formField) {
        this.formField = formField;
    }

    public String getUserInputValue() {
        return userInputValue;
    }

    public void setUserInputValue(String userInputValue) {
        this.userInputValue = userInputValue;
    }
}
