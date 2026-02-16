package com.ga.employeeSalary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class EmployeeSalaryService {
    private CSVProcessor csvProcessor;

    @Autowired
    private void setCSVProcessor(CSVProcessor csvProcessor){
        this.csvProcessor = csvProcessor;
    }

    public ResponseEntity<FileSystemResource> incrementSalaries(MultipartFile file){
        File outputfile = csvProcessor.UpdateSalaries(file,"Updated" + file.getOriginalFilename().substring(0,1).toUpperCase() + file.getOriginalFilename().substring(1));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + outputfile.getName())
                .contentType(MediaType.parseMediaType("text/csv"))
                .contentLength(outputfile.length())
                .body(new FileSystemResource(outputfile));
    }
}
