package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployeesBornBefore2000() {
        List<Employee> allEmployees = employeeRepository.findAll();

        return allEmployees.stream()
                .filter(employee -> employee.getBirthDate().before(new Date(100, 0, 1))) // Date(100, 0, 1) represents January 1, 2000
                .collect(Collectors.toList());
    }
}

package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date birthDate;

    // getters and setters
}

package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

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
