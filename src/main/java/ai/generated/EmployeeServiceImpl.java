package com.example.employee.core;

import com.example.employee.core.exception.EmployeeException;
import com.example.employee.core.repository.EmployeeRepository;
import com.example.employee.core.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployeeById(Long id) {
        // Begin of modification by AI
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeException("Employee not found"));

        if ("Femenino".equalsIgnoreCase(employee.getGender())) {
            throw new EmployeeException("Female employees cannot be deleted");
        }
        // End of modification by AI

        employeeRepository.deleteById(id);
    }

    // Other methods...
}

--- CÓDIGO JAVA TEST ---

