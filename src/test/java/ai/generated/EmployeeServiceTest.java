package test.core.api.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.impl.EmployeeServiceImpl;

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testDeleteFemaleEmployee() {
        MockitoAnnotations.initMocks(this);
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setGender("Femenino");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        assertThrows(IllegalArgumentException.class, () -> employeeService.deleteEmployeeById(1L));
    }

    @Test
    public void testDeleteNonExistingEmployee() {
        MockitoAnnotations.initMocks(this);
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> employeeService.deleteEmployeeById(1L));
    }
}
