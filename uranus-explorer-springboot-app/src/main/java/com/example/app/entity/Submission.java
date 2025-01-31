package com.example.app.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "submission")
public class Submission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Referencing the user table (assuming a User entity exists)

    @ManyToOne
    @JoinColumn(name = "e_service_id", nullable = false)
    private EService eService; // Referencing e_service table

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    private List<FormFieldValue> formFieldValues;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    private List<FormFieldDocument> formFieldDocuments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EService geteService() {
        return eService;
    }

    public void seteService(EService eService) {
        this.eService = eService;
    }

    public List<FormFieldValue> getFormFieldValues() {
        return formFieldValues;
    }

    public void setFormFieldValues(List<FormFieldValue> formFieldValues) {
        this.formFieldValues = formFieldValues;
    }

    public List<FormFieldDocument> getFormFieldDocuments() {
        return formFieldDocuments;
    }

    public void setFormFieldDocuments(List<FormFieldDocument> formFieldDocuments) {
        this.formFieldDocuments = formFieldDocuments;
    }
}
