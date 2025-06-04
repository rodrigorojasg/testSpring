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

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

package com.example.demo.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetEmployeesBornBefore2000() {
        Employee emp1 = new Employee();
        emp1.setBirthDate(LocalDate.of(1999, 1, 1));

        Employee emp2 = new Employee();
        emp2.setBirthDate(LocalDate.of(2000, 1, 1));

        Employee emp3 = new Employee();
        emp3.setBirthDate(LocalDate.of(2001, 1, 1));

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(emp1, emp2, emp3));

        List<Employee> employees = employeeService.getEmployeesBornBefore2000();
        assertEquals(1, employees.size());
        assertEquals(emp1.getBirthDate(), employees.get(0).getBirthDate());
    }
}
