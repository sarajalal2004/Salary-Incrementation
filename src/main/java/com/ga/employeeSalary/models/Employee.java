package com.ga.employeeSalary.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Employee {
    private Integer id;
    private String name;
    private Double salary;
    private LocalDate joinDate;
    private String role;
    private Double projectProgress;
    

}
