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

    // If the employee completed less than 60% of the projects they will NOT receive any increase in salary
    // On joined date, they'll receive 2% increase for each year worked only if they completed one year of work
    // Director receives 5% increase, Manager receives 2% increase. Employee receives 1% increase.

    public Double updateSalary(){
        if(this.projectProgress >= 0.6){
            double Increase = 1;
            if(joinDate.isBefore(LocalDate.now().minusYears(1))){
                Increase += (LocalDate.now().getYear() - joinDate.getYear()) * 0.2;
            }
            if(this.role.equals("Employee"))
                Increase += 0.1;
            else if(this.role.equals("Manager"))
                Increase += 0.2;
            else if(this.role.equals("Director"))
                Increase += 0.5;

            this.setSalary(this.salary * Increase);
        }
        return this.salary;
    }

    public String employeeToCSVLine(){
        return this.id + "," + this.name + "," + String.format("%.3f",this.salary) + "," + this.joinDate + "," + this.role + "," + this.projectProgress + "\n";
    }
}
