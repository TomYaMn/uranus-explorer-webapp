package com.example.app.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("${minio.endpoint}")
    private String minioUrl;

    @Value("${minio.access-key}")
    private String minioAccessKey;

    @Value("${minio.secret-key}")
    private String minioSecretKey;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Bean
    public MinioClient minioClient() {
        try {
            return MinioClient.builder()
                    .endpoint(minioUrl)
                    .credentials(minioAccessKey, minioSecretKey)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing MinIO client", e);
        }
    }
}