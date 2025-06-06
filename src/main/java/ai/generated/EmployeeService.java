package com.company.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        // Start of AI modification
        if ("Femenino".equals(employee.getGender())) {
            throw new UnsupportedOperationException("No se permite eliminar empleados de género femenino");
        }
        // End of AI modification

        employeeRepository.delete(employee);
    }
}

