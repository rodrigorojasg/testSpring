package com.example.demo.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

