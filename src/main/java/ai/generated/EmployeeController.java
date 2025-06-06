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
    // Other fields...

    @Autowired
    private EmployeeService employeeService;

    // Other methods...

    @GetMapping("/born-before-2000")
    public List<Employee> getEmployeesBornBefore2000() {
        return employeeService.findEmployeesBornBefore2000();
    }
}
```

```java
