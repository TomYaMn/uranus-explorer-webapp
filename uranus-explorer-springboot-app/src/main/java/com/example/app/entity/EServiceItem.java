package com.example.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "e_service_item")
public class EServiceItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "e_service_item", nullable = false)
    private String eServiceItem;

    @ManyToOne
    @JoinColumn(name = "e_service_id", nullable = false)
    private EService eService;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEServiceItem() {
        return eServiceItem;
    }

    public void setEServiceItem(String eServiceItem) {
        this.eServiceItem = eServiceItem;
    }

    public EService geteService() {
        return eService;
    }

    public void seteService(EService eService) {
        this.eService = eService;
    }
}