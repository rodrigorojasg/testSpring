package test.core.api.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    // getters and setters
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

    @Query("SELECT e FROM Employee e WHERE e.birthDate < :date")
    List<Employee> findAllBornBefore(LocalDate date);
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
        LocalDate date = LocalDate.of(year, 1, 1);
        return employeeRepository.findAllBornBefore(date);
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testGetEmployeesBornBefore2000() {
        List<Employee> employees = employeeService.getEmployeesBornBeforeYear(2000);
        assertTrue(employees.stream().allMatch(e -> e.getBirthDate().getYear() < 2000));
    }
}
