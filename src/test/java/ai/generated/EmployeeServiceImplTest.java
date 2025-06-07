package com.example.demo.service.impl;

import com.example.demo.entity.Employee;
import com.example.demo.exception.GenderException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
    public void deleteEmployeeById_FemaleEmployee_ThrowsGenderException() {
        Employee employee = new Employee();
        employee.setGender("Femenino");
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        assertThrows(GenderException.class, () -> employeeService.deleteEmployeeById(1L));

        verify(employeeRepository, never()).deleteById(anyLong());
    }

    @Test
    public void deleteEmployeeById_MaleEmployee_DeletesEmployee() {
        Employee employee = new Employee();
        employee.setGender("Masculino");
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        employeeService.deleteEmployeeById(1L);

        verify(employeeRepository).deleteById(anyLong());
    }
}
