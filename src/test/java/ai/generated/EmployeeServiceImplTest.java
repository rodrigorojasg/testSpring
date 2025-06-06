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
