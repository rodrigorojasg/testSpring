package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetEmployeesBornBefore2000() {
        Employee employee1 = new Employee();
        employee1.setBirthDate(new Date(99, 0, 1)); // January 1, 1999

        Employee employee2 = new Employee();
        employee2.setBirthDate(new Date(100, 0, 1)); // January 1, 2000

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> employeesBornBefore2000 = employeeService.getEmployeesBornBefore2000();

        assertEquals(1, employeesBornBefore2000.size());
        assertEquals(employee1, employeesBornBefore2000.get(0));
    }
}
