package com.example.demo.service;

import com.example.demo.exception.GenderValidationException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void validateAndDeleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        // Start of AI modification
        if (employee != null && "Femenino".equals(employee.getGender())) {
            throw new GenderValidationException("Cannot delete employee with gender 'Femenino'");
        }
        // End of AI modification

        employeeRepository.deleteById(id);
    }
}

