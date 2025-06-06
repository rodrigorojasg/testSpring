package test.core.api.controller;

import org.springframework.http.ResponseEntity;
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

    // Other methods...

    @GetMapping("/born-before-2000")
    public ResponseEntity<List<Employee>> getEmployeesBornBefore2000() {
        return ResponseEntity.ok(employeeService.findEmployeesBornBefore2000());
    }
}
```

```java
