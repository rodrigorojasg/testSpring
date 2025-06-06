package com.example.demo.employee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testDeleteEmployee_Female() {
        Employee femaleEmployee = new Employee();
        femaleEmployee.setId(1L);
        femaleEmployee.setGender("Femenino");

        given(employeeRepository.findById(1L)).willReturn(Optional.of(femaleEmployee));

        try {
            employeeService.deleteEmployee(1L);
        } catch (Exception e) {
            // Expected exception
        }

        verify(employeeRepository, never()).deleteById(1L);
    }

    @Test
    public void testDeleteEmployee_Male() throws Exception {
        Employee maleEmployee = new Employee();
        maleEmployee.setId(2L);
        maleEmployee.setGender("Masculino");

        given(employeeRepository.findById(2L)).willReturn(Optional.of(maleEmployee));

        employeeService.deleteEmployee(2L);

        verify(employeeRepository).deleteById(2L);
    }
}
```
