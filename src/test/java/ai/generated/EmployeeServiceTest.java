package com.example.employee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testDeleteEmployee_Female() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setGender("Femenino");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            employeeService.deleteEmployee(1L);
        });

        String expectedMessage = "Cannot delete female employees due to internal regulations";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDeleteEmployee_Male() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setGender("Masculino");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).delete(employee);
    }
}
