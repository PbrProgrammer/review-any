package org.alima.controller;


import lombok.RequiredArgsConstructor;
import org.alima.exception.BadRequestException;
import org.alima.service.ImageService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<String> createImage(@RequestPart("file") MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new BadRequestException("فایل نباید خالی باشد", "4001");
        }
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();

//        if (fileName == null || !fileName.endsWith(".xlsx")) {
//            throw new ChnRequestSimpleException("پسوند فایل باید .xlsx باشد");
//        }
        return ResponseEntity.ok(imageService.uploadFile(file));
    }


//    @GetMapping("/download")
////    @PreAuthorize(value = "hasAuthority('" + SSOConstant.CIP_BATCH_VALIDATION + "')")
//    @ResponseStatus(HttpStatus.OK)
//    public CompletableFuture<ResponseEntity<byte[]>> downloadExcel(@RequestParam(required = true) String fileGuid,
//                                                                   @RequestParam(required = true) CbaFileType fileType) {
//
//        DownloadFileDto downloadFileDto = cbaFileService.downloadFile(fileGuid, fileType);
//        return prepareFileForDownload(downloadFileDto, fileType);
//
//    }
//
//    @NotNull
//    private static CompletableFuture<ResponseEntity<byte[]>> prepareFileForDownload(DownloadFileDto downloadFileDto, CbaFileType fileType) {
//        byte[] fileContent = downloadFileDto.getFileContents();
//        String fileName = downloadFileDto.getFileName();
//
//        HttpHeaders headers = new HttpHeaders();
//
//        String encodedFileName = UriUtils.encode(fileName, StandardCharsets.UTF_8);
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"; filename*=UTF-8''" + encodedFileName);
//
//        if (fileType.equals(CbaFileType.FINAL) || fileType.equals(CbaFileType.FINAL_PURE)) {
//            headers.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8));
//        } else {
//            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
//        }
//
//        return CompletableFuture.completedFuture(
//                new ResponseEntity<>(fileContent, headers, HttpStatus.OK)
//        );
//    }
}
