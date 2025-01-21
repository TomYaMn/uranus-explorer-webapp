package com.example.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUserDto {
    private String email;

    private String password;
    @JsonProperty("username")
    private String userName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

// getters and setters here...
}
