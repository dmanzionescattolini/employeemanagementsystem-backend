package com.blit.employeemanagementsystem.repositories;

import com.blit.employeemanagementsystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}