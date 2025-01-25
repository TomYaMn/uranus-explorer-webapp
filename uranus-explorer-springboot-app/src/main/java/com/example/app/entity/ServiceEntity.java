package com.example.app.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "services")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<ServiceItem> serviceItems;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<FormField> formFields;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ServiceItem> getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(List<ServiceItem> serviceItems) {
        this.serviceItems = serviceItems;
    }

    public List<FormField> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<FormField> formFields) {
        this.formFields = formFields;
    }
}
