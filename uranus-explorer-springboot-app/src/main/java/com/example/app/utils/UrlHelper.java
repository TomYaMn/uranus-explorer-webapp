package com.example.app.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlHelper {

    private final String minioBaseUrl;
    private final String bucketName;

    // Constructor to inject values from application.properties
    public UrlHelper(
            @Value("${minio.endpoint}") String minioBaseUrl,
            @Value("${minio.bucket-name}") String bucketName) {
        this.minioBaseUrl = minioBaseUrl.endsWith("/") ? minioBaseUrl : minioBaseUrl + "/";
        this.bucketName = bucketName;
    }

    // Generate document URL dynamically
    public String generateDocumentUrl(Long formFieldId, String documentType) {
        String filePath = "documents/" + formFieldId + "." + documentType;
        return minioBaseUrl + bucketName + "/" + filePath;
    }
}