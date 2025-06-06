package com.company.employee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void deleteEmployee_MaleEmployee_Success() {
        Employee maleEmployee = new Employee();
        maleEmployee.setId(1L);
        maleEmployee.setGender("Masculino");

        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(maleEmployee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).delete(maleEmployee);
    }

    @Test
    public void deleteEmployee_FemaleEmployee_ThrowsException() {
        Employee femaleEmployee = new Employee();
        femaleEmployee.setId(2L);
        femaleEmployee.setGender("Femenino");

        when(employeeRepository.findById(2L)).thenReturn(java.util.Optional.of(femaleEmployee));

        assertThrows(UnsupportedOperationException.class, () -> employeeService.deleteEmployee(2L));

        verify(employeeRepository, never()).delete(femaleEmployee);
    }
}
