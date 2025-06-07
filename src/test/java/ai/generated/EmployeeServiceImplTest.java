package com.example.employee.core;

import com.example.employee.core.exception.EmployeeException;
import com.example.employee.core.repository.EmployeeRepository;
import com.example.employee.core.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void deleteFemaleEmployeeById_ShouldThrowException() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setGender("Femenino");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        assertThrows(EmployeeException.class, () -> employeeService.deleteEmployeeById(1L));
    }

    @Test
    public void deleteMaleEmployeeById_ShouldNotThrowException() {
        Employee employee = new Employee();
        employee.setId(2L);
        employee.setGender("Masculino");

        when(employeeRepository.findById(2L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployeeById(2L);
    }

    @Test
    public void deleteOtherGenderEmployeeById_ShouldNotThrowException() {
        Employee employee = new Employee();
        employee.setId(3L);
        employee.setGender("Other");

        when(employeeRepository.findById(3L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployeeById(3L);
    }

    // Other tests...
}
