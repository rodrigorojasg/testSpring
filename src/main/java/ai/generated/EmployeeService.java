package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployeesBornBefore2000() {
        List<Employee> allEmployees = employeeRepository.findAll();

        return allEmployees.stream()
                .filter(employee -> employee.getBirthDate().before(new Date(100, 0, 1))) // Date(100, 0, 1) represents January 1, 2000
                .collect(Collectors.toList());
    }
}

