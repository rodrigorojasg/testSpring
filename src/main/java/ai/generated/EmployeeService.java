package com.example.demo.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        // Start of AI modification
        if (employee.getGender().equalsIgnoreCase("Femenino")) {
            throw new UnsupportedOperationException("Cannot delete female employees due to internal regulations");
        }
        // End of AI modification

        employeeRepository.delete(employee);
    }
}
```

```java
