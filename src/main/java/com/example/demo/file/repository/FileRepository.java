package com.example.demo.file.repository;

import com.example.demo.file.domain.FileProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class FileRepository {
    public void save(FileProperty fileProperty, MultipartFile multipartFile) {
        try{
            File fsFile = new File(fileProperty.getFilePath());
            multipartFile.transferTo(fsFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(FileProperty fileProperty) {
        try{
            File fsFile = new File(fileProperty.getFilePath());
            Files.deleteIfExists(fsFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
