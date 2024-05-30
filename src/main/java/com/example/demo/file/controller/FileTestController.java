package com.example.demo.file.controller;

import com.example.demo.api.response.ApiResponse;
import com.example.demo.api.response.ResponseCode;
import com.example.demo.file.domain.FilePropertyResponse;
import com.example.demo.file.service.FilePropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileTestController {
    private final FilePropertyService filePropertyService;
    @GetMapping
    public String fileUpload(){
        return "file-upload";
    }
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping
    public ApiResponse<FilePropertyResponse> uploadFile(@ModelAttribute ShopCreateForm form) throws IOException {
        MultipartFile attachedFile = form.getAttachedFile();
        List<MultipartFile> imageFiles = form.getImageFiles();
        //null처리와 response 하나로 병합 필요
        FilePropertyResponse filePropertyResponse = filePropertyService.storeFile(attachedFile);
        List<FilePropertyResponse> filePropertyResponses = filePropertyService.storeFiles(imageFiles);
        return ApiResponse.response(ResponseCode.Created, filePropertyResponse);
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public ApiResponse<FilePropertyResponse> deleteFile(@PathVariable Long id){
        return ApiResponse.response(ResponseCode.OK, filePropertyService.delete(id));
    }
}
