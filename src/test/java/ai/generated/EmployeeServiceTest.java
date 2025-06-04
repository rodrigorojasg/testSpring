package com.example.demo.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testGetEmployeesBornBefore2000() {
        List<Employee> employees = employeeService.getEmployeesBornBefore2000();
        assertTrue(employees.stream().allMatch(employee -> employee.getBirthDate().getYear() < 2000));
    }
}
