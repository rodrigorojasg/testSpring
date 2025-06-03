package test.core.api.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}

package test.core.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.core.api.model.Employee;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.birthDate < :year")
    List<Employee> findEmployeesBornBeforeYear(LocalDate year);
}

package test.core.api.service;

import test.core.api.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeesBornBeforeYear(int year);
}

package test.core.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployeesBornBeforeYear(int year) {
        return employeeRepository.findEmployeesBornBeforeYear(LocalDate.of(year, 1, 1));
    }
}

package test.core.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/born-before-2000")
    public List<Employee> getEmployeesBornBefore2000() {
        return employeeService.getEmployeesBornBeforeYear(2000);
    }
}

package test.core.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeCoreApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeCoreApiApplication.class, args);
    }
}

package test.core.api.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.impl.EmployeeServiceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetEmployeesBornBeforeYear() {
        Employee employee1 = new Employee();
        employee1.setBirthDate(LocalDate.of(1990, 1, 1));

        Employee employee2 = new Employee();
        employee2.setBirthDate(LocalDate.of(1985, 1, 1));

        Mockito.when(employeeRepository.findEmployeesBornBeforeYear(LocalDate.of(2000, 1, 1)))
                .thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> employees = employeeService.getEmployeesBornBeforeYear(2000);

        assertEquals(2, employees.size());
        assertEquals(employee1, employees.get(0));
        assertEquals(employee2, employees.get(1));
    }
}
