package com.ga.employeeSalary.controllers;

import com.ga.employeeSalary.services.EmployeeSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api/salary")
public class EmployeeSalaryController {
    private EmployeeSalaryService employeeSalaryService;

    @Autowired
    private void setEmployeeSalaryService(EmployeeSalaryService employeeSalaryService){
        this.employeeSalaryService = employeeSalaryService;
    }

    /**
     * post controller for getting and return
     * the file after using service to update it
     *
     * @param input     the uploaded CSV file containing employee data
     * @return          the generated file with updated salaries
     */
    @PostMapping(path = "/increment", consumes = "multipart/form-data")
    public ResponseEntity<FileSystemResource> incrementSalaries(@RequestParam("file") MultipartFile input){
        return employeeSalaryService.incrementSalaries(input);
    }
}
