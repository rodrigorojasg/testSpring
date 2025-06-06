package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetEmployeesBornBefore2000() {
        Employee employee1 = new Employee();
        employee1.setBirthDate(LocalDate.of(1990, 1, 1));

        Employee employee2 = new Employee();
        employee2.setBirthDate(LocalDate.of(2000, 1, 1));

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> result = employeeService.getEmployeesBornBefore2000();

        assertEquals(1, result.size());
        assertEquals(employee1, result.get(0));
    }
}
