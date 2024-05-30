package com.example.demo.file.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FileProperty {
    @Id
    @GeneratedValue
    private Long id;
    private String uploadFileName; //사용자가 업로드한 파일 이름
    private String storedFileName; //실제 FS에 저장되는 파일 이름
    private String filePath;
    private String fileUrl;
    private Long fileSize;
    private String contentType;
    private LocalDateTime uploadTime;
}
