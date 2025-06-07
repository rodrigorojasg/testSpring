package com.example.demo.service;

import com.example.demo.exception.GenderValidationException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void validateAndDeleteEmployeeById_GenderFemenino_ThrowsException() {
        Employee employee = new Employee();
        employee.setGender("Femenino");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        assertThrows(GenderValidationException.class, () -> {
            employeeService.validateAndDeleteEmployeeById(1L);
        });

        verify(employeeRepository, Mockito.never()).deleteById(1L);
    }

    @Test
    public void validateAndDeleteEmployeeById_GenderMasculino_DeletesEmployee() {
        Employee employee = new Employee();
        employee.setGender("Masculino");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.validateAndDeleteEmployeeById(1L);

        verify(employeeRepository).deleteById(1L);
    }
}
