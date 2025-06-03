package test.core.api.service;

import test.core.api.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    List<Employee> findAllBornBefore(LocalDate date);
}

