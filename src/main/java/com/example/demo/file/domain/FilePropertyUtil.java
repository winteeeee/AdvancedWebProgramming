package com.example.demo.file.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
public class FilePropertyUtil {
    @Value("${file.root-path}")
    private String FILE_ROOT_PATH;
    @Value("${file.root-url}")
    private String FILE_ROOT_URL;
    public FileProperty createFileProperty(MultipartFile multipartFile){
        String uploadedFileName = multipartFile.getOriginalFilename();
        String storedFileName = createStoredFileName(uploadedFileName);
        FileProperty fileProperty = FileProperty.builder()
                .uploadFileName(uploadedFileName)
                .storedFileName(storedFileName)
                .filePath(createFilePath(storedFileName))
                .fileUrl(createFileUrl(storedFileName))
                .fileSize(multipartFile.getSize())
                .contentType(multipartFile.getContentType())
                .uploadTime(LocalDateTime.now())
                .build();
        return fileProperty;
    }
    private static String createStoredFileName(String uploadedFileName) {
        String ext = uploadedFileName.substring(uploadedFileName.lastIndexOf('.')); //확장자
        return UUID.randomUUID().toString().replace("-","").concat(ext);
    }
    private String createFilePath(String storedFileName) {
        return String.format("%s/%s", FILE_ROOT_PATH, storedFileName);
    }
    private String createFileUrl(String storedFileName) {
        return String.format("%s/%s", FILE_ROOT_URL, storedFileName);
    }
}