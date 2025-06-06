package com.example.demo.service;

import com.example.demo.exception.GenderException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        
        // Start of AI modification
        if(employee.getGender().equalsIgnoreCase("Femenino")) {
            throw new GenderException("Cannot delete employee of gender 'Femenino'");
        }
        // End of AI modification

        employeeRepository.delete(employee);
    }
}
