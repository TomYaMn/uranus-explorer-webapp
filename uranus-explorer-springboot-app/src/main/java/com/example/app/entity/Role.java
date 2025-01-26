package com.example.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", unique = true)  // Enforce uniqueness of role names
    private String roleName;  // e.g., "ROLE_USER", "ROLE_ADMIN"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Getter and setter for user
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter and setter for roleName
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    // Getter and setter for id (Optional as BaseEntity may have id)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
