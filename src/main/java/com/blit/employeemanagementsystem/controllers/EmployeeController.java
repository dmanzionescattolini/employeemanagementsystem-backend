package com.blit.employeemanagementsystem.controllers;


import com.blit.employeemanagementsystem.exceptions.EmployeeInformationMissingException;
import com.blit.employeemanagementsystem.exceptions.NoSuchEmployeeException;
import com.blit.employeemanagementsystem.models.Employee;
import com.blit.employeemanagementsystem.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
        try{
           return ResponseEntity.ok(employeeService.createEmployee(employee).toString());
        }catch(EmployeeInformationMissingException e){
            return new ResponseEntity<String>("Field missing in employee information trying to be added",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(employeeService.getEmployeeById(id));
        }catch(NoSuchEmployeeException e){
            return new ResponseEntity<>("No employee with provided id exists",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee){
        try {
            return ResponseEntity.ok(employeeService.updateEmployee(id, updatedEmployee));
        }catch(NoSuchEmployeeException e){
            return ResponseEntity.badRequest().body("No employee with provided id exists");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id){
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee deleted successfully");
        }catch(NoSuchEmployeeException e){
            return ResponseEntity.badRequest().body("No employee with provided id exists");
        }

    }

    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestBody Employee employee){
        try{
            employeeService.deleteEmployee(employee);
            return ResponseEntity.ok("Employee deleted successfully");
        }catch(NoSuchEmployeeException e){
            return ResponseEntity.badRequest().body("No employee with provided id exists");

        }
    }


}
