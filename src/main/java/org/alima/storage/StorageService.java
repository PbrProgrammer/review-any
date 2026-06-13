package org.alima.storage;


import java.io.InputStream;

public interface StorageService {

    String upload(
            InputStream inputStream,
            long contentLength,
            String contentType,
            String fileName
    );

    void delete(String objectKey);

    String generatePresignedUrl(String objectKey, int expirationMinutes);
}

