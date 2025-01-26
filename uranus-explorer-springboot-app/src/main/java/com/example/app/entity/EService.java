package com.example.app.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "e_service")
public class EService extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "e_service_name", nullable = false)
    private String eServiceName;

    @OneToMany(mappedBy = "eService", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<EServiceItem> eServiceItems;

    @OneToMany(mappedBy = "eService", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<FormField> formFields;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String geteServiceName() {
        return eServiceName;
    }

    public void seteServiceName(String eServiceName) {
        this.eServiceName = eServiceName;
    }

    public List<EServiceItem> getEServiceItems() {
        return eServiceItems;
    }

    public void setEServiceItems(List<EServiceItem> eServiceItems) {
        this.eServiceItems = eServiceItems;
    }

    public List<FormField> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<FormField> formFields) {
        this.formFields = formFields;
    }
}

