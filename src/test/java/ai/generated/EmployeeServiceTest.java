package com.example.demo.service;

import com.example.demo.exception.GenderException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void deleteEmployee_GenderFemenino_ThrowsGenderException() {
        Employee employee = new Employee();
        employee.setGender("Femenino");

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        assertThrows(GenderException.class, () -> employeeService.deleteEmployee(1L));
    }

    @Test
    public void deleteEmployee_GenderMasculino_DeletesSuccessfully() {
        Employee employee = new Employee();
        employee.setGender("Masculino");

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);
        Mockito.verify(employeeRepository, Mockito.times(1)).delete(employee);
    }
}
