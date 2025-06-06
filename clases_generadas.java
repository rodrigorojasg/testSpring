--- ANÁLISIS (Fase 1) ---
1. Clases que pueden reutilizarse sin modificar:
   - Employee.java
   - EmployeeCoreApiApplication.java
2. Clases que deben ser modificadas:
   - EmployeeRepository.java
   - EmployeeController.java
   - EmployeeService.java
   - EmployeeServiceImpl.java
3. Clases que deben ser completamente nuevas:
   - No hay ninguna.

--- PLAN (Fase 2) ---
- EmployeeRepository.java: Se agregará un nuevo método para buscar empleados que hayan nacido antes del año 2000.
- EmployeeController.java: Se agregará un nuevo endpoint para obtener los empleados que hayan nacido antes del año 2000.
- EmployeeService.java: Se agregará un nuevo método para obtener los empleados que hayan nacido antes del año 2000.
- EmployeeServiceImpl.java: Se implementará el nuevo método definido en EmployeeService.java.

--- GENERACIÓN (Fase 3) ---

```java
package test.core.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.core.api.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE YEAR(e.birthDate) < 2000")
    List<Employee> findEmployeesBornBefore2000();
}
```

```java
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
package test.core.api.service;

import test.core.api.model.Employee;

import java.util.List;

public interface EmployeeService {
    // Other methods...

    List<Employee> findEmployeesBornBefore2000();
}
```

```java
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
