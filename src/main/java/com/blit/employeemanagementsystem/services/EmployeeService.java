package com.blit.employeemanagementsystem.services;


import com.blit.employeemanagementsystem.exceptions.EmployeeInformationMissingException;
import com.blit.employeemanagementsystem.exceptions.NoSuchEmployeeException;
import com.blit.employeemanagementsystem.models.Employee;
import com.blit.employeemanagementsystem.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public Employee createEmployee(Employee newEmployee) throws EmployeeInformationMissingException {
        if(newEmployee.getFirstName()==null || newEmployee.getEmail()==null) {
            throw new EmployeeInformationMissingException();
        }

        return employeeRepository.save(newEmployee);
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) throws NoSuchEmployeeException {
        return employeeRepository.findById(id).orElseThrow(()-> new NoSuchEmployeeException());
    }

    public Employee updateEmployee(Long id, Employee employee) throws NoSuchEmployeeException {
        Employee oldEmployee = getEmployeeById(id);

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(id);
        if (employee.getFirstName() != null) {
            updatedEmployee.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null) {
            updatedEmployee.setLastName(employee.getLastName());
        }
        if (employee.getEmail() != null) {
            updatedEmployee.setEmail(employee.getEmail());
        }
        return employeeRepository.save(updatedEmployee);

    }

    public void deleteEmployee(Long id) throws NoSuchEmployeeException{
        if(!employeeRepository.existsById(id)){
            throw new NoSuchEmployeeException();
        }
        employeeRepository.deleteById(id);
    }

    public void deleteEmployee(Employee employee) throws NoSuchEmployeeException{
        if(employee.getId()==null || !employeeRepository.existsById(employee.getId())){
            throw new NoSuchEmployeeException();
        }
        employeeRepository.delete(employee);
    }






}
