package com.example.app.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public String uploadFile(Long formFieldId, MultipartFile file) {
        try {
            // Generate a unique filename using formFieldId and a UUID
            String fileName = "documents/" + formFieldId + "_" + UUID.randomUUID() + "." + getFileExtension(file.getOriginalFilename());

            // Get file input stream
            InputStream inputStream = file.getInputStream();

            // Upload file to MinIO
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            // Return the generated file URL
            return "http://localhost:9000/" + bucketName + "/" + fileName;

        } catch (MinioException e) {
            throw new RuntimeException("Error uploading file to MinIO: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("File upload failed: " + e.getMessage(), e);
        }
    }

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
