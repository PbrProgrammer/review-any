package org.alima.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alima.exception.BadRequestException;
import org.alima.model.Images;
import org.alima.repository.ImageRepository;
import org.alima.storage.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {



    private final StorageService storageService;
    private final ImageRepository imageRepository;
//    @Value("${minio.cba.open.account.final.files.bucket}")
//    private String finalFilesBucket;
//    @Value("${minio.cba.open.account.final.pure.files.bucket}")
//    private String finalPureFilesBucket;
//    @Value("${minio.cba.open.account.fail.files.bucket}")
//    private String failFilesBucket;
//    @Value("${minio.cba.open.account.sample.files.bucket}")
//    private String sampleFilesBucket;
//    @Value("${minio.cba.open.account.source.files.bucket}")
//    private String sourceFileBucket;

//    @Override
//    public DownloadFileDto downloadFile(String fileGuid, CbaFileType fileType) {
//
//        byte[] bytes = null;
//        String fileName = null;
//        String fileBucket = null;
//        try {
//            if (fileGuid != null && !fileGuid.isEmpty()) {
//                switch (fileType) {
//                    case SAMPLE -> {
//                        fileBucket = sampleFilesBucket;
//                        fileName = "Sample_" + System.currentTimeMillis();
//                        bytes = minioService.get(fileBucket, fileGuid + ".xlsx").readAllBytes();
//                    }
//
//                    case FAILED -> {
//                        fileBucket = failFilesBucket;
//                        fileName = "Error_" + System.currentTimeMillis();
//                        bytes = minioService.get(fileBucket, fileGuid + ".xlsx").readAllBytes();
//                    }
//
//                    case SOURCE -> {
//                        fileBucket = sourceFileBucket;
//                        fileName = "Source_" + System.currentTimeMillis();
//                        bytes = minioService.get(fileBucket, fileGuid + ".xlsx").readAllBytes();
//                    }
//
//                    case FINAL -> {
//                        fileBucket = finalFilesBucket;
//                        fileName = "Final_" + System.currentTimeMillis();
//                        bytes = minioService.get(fileBucket, fileGuid + ".txt").readAllBytes();
//                    }
//
//                    case FINAL_PURE -> {
//                        fileBucket = finalPureFilesBucket;
//                        fileName = "FinalPure_" + System.currentTimeMillis();
//                        bytes = minioService.get(fileBucket, fileGuid + ".txt").readAllBytes();
//                    }
//                }
//            }
//        } catch (ErrorResponseException e) {
//            log.error("error has producer", e);
//            throw new ChnRequestSimpleException("کاربر گرامی فایلی برای دریافت وجود ندارد.");
//        } catch (ServerException | InsufficientDataException | IOException |
//                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
//                 InternalException e) {
//            log.error("error has occurred", e);
//            throw new RuntimeException(e);
//        }
//        return DownloadFileDto.builder()
//                .fileContents(bytes)
//                .fileName(fileName)
//                .build();
//    }

    @Transactional
    public String uploadFile(MultipartFile file) {
        byte[] fileBytes;
        String key;
        try (InputStream inputStream = file.getInputStream()) {
            fileBytes = inputStream.readAllBytes();
        } catch (IOException e) {
            throw new BadRequestException("خطا در دریافت فایل", "4002");
        }
        String objectKey = UUID.randomUUID() + "-" + file.getOriginalFilename();
        try (InputStream inputStream = new ByteArrayInputStream(fileBytes)) {
            key = storageService.upload(
                    inputStream,
                    file.getSize(),
                    file.getContentType(),
                    objectKey
            );
        } catch (Exception e) {
            log.error("Error in OpenAccountFileService in put file to minio", e);
            throw new BadRequestException("خطا در آپلود فایل", "4003");
        }
        Images images = new Images();
        images.setImageUrl(key);
        imageRepository.save(images);
        return key;

    }

    public List<Images> getImagesByReviewId(Long id) {
        return imageRepository.findImagesByReviewId(id);

    }

    public List<Images> getImagesByImageUrl(List<String> imageUrls) {
        return imageRepository.findImagesByImageUrlIn(imageUrls);

    }


}

