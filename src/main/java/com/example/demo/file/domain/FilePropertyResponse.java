package com.example.demo.file.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FilePropertyResponse {
    private Long id;
    private String originalFileName;
    private String storedFileName;
    private String fileUrl;
    private Long fileSize;
    private String contentType;
    private LocalDateTime uploadTime;
    public static FilePropertyResponse of(FileProperty fileProperty){
        if(fileProperty == null)
            return null;
        return FilePropertyResponse.builder()
                .id(fileProperty.getId())
                .originalFileName(fileProperty.getUploadFileName())
                .storedFileName(fileProperty.getStoredFileName())
                .fileUrl(fileProperty.getFileUrl())
                .fileSize(fileProperty.getFileSize())
                .contentType(fileProperty.getContentType())
                .uploadTime(fileProperty.getUploadTime())
                .build();
    }
}
