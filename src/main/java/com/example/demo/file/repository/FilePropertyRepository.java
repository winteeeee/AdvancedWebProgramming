package com.example.demo.file.repository;

import com.example.demo.file.domain.FileProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilePropertyRepository extends JpaRepository<FileProperty, Long> {
    boolean existsByUploadFileName(String name);
}
