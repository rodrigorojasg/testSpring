package com.example.demo.service.impl;

import com.example.demo.entity.Employee;
import com.example.demo.exception.GenderException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Other methods...

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Start of AI modification
        if ("Femenino".equalsIgnoreCase(employee.getGender())) {
            throw new GenderException("Cannot delete female employees due to internal regulations");
        }
        // End of AI modification

        employeeRepository.deleteById(id);
    }
}

