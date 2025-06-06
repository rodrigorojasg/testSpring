package com.example.EmployeeCoreApi.service.impl;

import com.example.EmployeeCoreApi.exception.EmployeeException;
import com.example.EmployeeCoreApi.model.Employee;
import com.example.EmployeeCoreApi.repository.EmployeeRepository;
import com.example.EmployeeCoreApi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            // BEGIN MODIFICATION BY AI
            if ("Femenino".equals(employee.getGender())) {
                throw new EmployeeException("No se permite eliminar empleados de género femenino");
            }
            // END MODIFICATION BY AI

            employeeRepository.deleteById(id);
        } else {
            throw new EmployeeException("Empleado no encontrado");
        }
    }

    // Other methods...
}

// Test class
