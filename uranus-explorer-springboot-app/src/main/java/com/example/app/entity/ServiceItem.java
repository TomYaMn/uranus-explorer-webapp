package com.example.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service_items")
public class ServiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity service;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }
}