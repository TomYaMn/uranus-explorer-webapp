package com.example.app.dto;

public class ErrorResponseDto {
    private int status;
    private String message;
    private String path;

    public ErrorResponseDto(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
    }

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
