package com.example.demo.employee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testDeleteFemaleEmployeeThrowsException() {
        Employee femaleEmployee = new Employee();
        femaleEmployee.setId(1L);
        femaleEmployee.setGender("Femenino");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(femaleEmployee));

        assertThrows(UnsupportedOperationException.class, () -> employeeService.deleteEmployee(1L));

        verify(employeeRepository, never()).delete(femaleEmployee);
    }

    @Test
    public void testDeleteMaleEmployee() {
        Employee maleEmployee = new Employee();
        maleEmployee.setId(1L);
        maleEmployee.setGender("Masculino");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(maleEmployee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).delete(maleEmployee);
    }
}
```
