package com.example.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "form_field_value")
public class FormFieldValue extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "form_field_id", nullable = false)
    private FormField formField;

    @Column(name = "user_input_value", nullable = false, columnDefinition = "TEXT")
    private String userInputValue;

    @Column(name = "user_id", nullable = false)
    private Long userId;

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

    public String getUserInputValue() {
        return userInputValue;
    }

    public void setUserInputValue(String userInputValue) {
        this.userInputValue = userInputValue;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
