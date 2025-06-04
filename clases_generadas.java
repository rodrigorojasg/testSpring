package com.example.demo.employee;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployeesBornBefore2000() {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getBirthDate().getYear() < 2000)
                .collect(Collectors.toList());
    }
}

package com.example.demo.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

package com.example.demo.employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate birthDate;

    // getters and setters
}

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
