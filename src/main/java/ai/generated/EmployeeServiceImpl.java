package test.core.api.service.impl;

import org.springframework.stereotype.Service;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Other methods...

    @Override
    public List<Employee> findEmployeesBornBefore2000() {
        return employeeRepository.findEmployeesBornBefore2000();
    }
}
```
