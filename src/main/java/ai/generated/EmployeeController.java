package test.core.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/birthdays")
    public List<Employee> getEmployeesWithBirthdayThisMonth() {
        return employeeService.findEmployeesWithBirthdayThisMonth();
    }
}
