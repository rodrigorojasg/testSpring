package com.example.demo.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void deleteEmployee(Long id) throws Exception {
        // Start of AI modification
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new Exception("Employee not found"));

        if (employee.getGender().equalsIgnoreCase("Femenino")) {
            throw new Exception("Cannot delete female employee due to internal regulations");
        }
        // End of AI modification

        employeeRepository.deleteById(id);
    }
}
```

```java
