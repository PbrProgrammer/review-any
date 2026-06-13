package org.alima.storage;

import lombok.RequiredArgsConstructor;
import org.alima.config.StorageProperties;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioStorageService implements StorageService {

    private final S3Client s3Client;
    private final StorageProperties properties;

    @Override
    public String upload(InputStream inputStream,
                         long contentLength,
                         String contentType,
                         String objectKey) {

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(properties.getBucket())
                .key(objectKey)
                .contentType(contentType)
                .build();

        s3Client.putObject(
                request,
                RequestBody.fromInputStream(inputStream, contentLength)
        );

        return objectKey;
    }

    @Override
    public void delete(String objectKey) {

        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(properties.getBucket())
                .key(objectKey)
                .build();

        s3Client.deleteObject(request);
    }

    @Override
    public String generatePresignedUrl(String objectKey, int expirationMinutes) {
        // فعلاً ساده نگه می‌داریم
        return properties.getEndpoint()
                + "/"
                + properties.getBucket()
                + "/"
                + objectKey;
    }
}

