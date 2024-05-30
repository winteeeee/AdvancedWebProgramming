package com.example.demo.file.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ShopCreateForm {
    private String itemName;
    private MultipartFile attachedFile;
    private List<MultipartFile> imageFiles;
}
