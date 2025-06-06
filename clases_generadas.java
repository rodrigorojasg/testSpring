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
package com.example.EmployeeCoreApi.service.impl;

import com.example.EmployeeCoreApi.exception.EmployeeException;
import com.example.EmployeeCoreApi.model.Employee;
import com.example.EmployeeCoreApi.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteEmployeeById_FemaleEmployee_ExceptionThrown() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setGender("Femenino");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        assertThrows(EmployeeException.class, () -> employeeService.deleteEmployeeById(1L));
    }

    @Test
    public void testDeleteEmployeeById_MaleEmployee_NoExceptionThrown() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setGender("Masculino");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployeeById(1L); // No exception thrown
    }
}
