--- ANÁLISIS (Fase 1) ---
1. Las clases que pueden reutilizarse sin modificar son: Employee.java, EmployeeCoreApiApplication.java
2. Las clases que deben ser modificadas son: EmployeeRepository.java, EmployeeService.java, EmployeeServiceImpl.java, EmployeeController.java
3. No se necesitan clases completamente nuevas.

--- PLAN (Fase 2) ---
- EmployeeRepository.java: Se agregará un nuevo método para buscar empleados nacidos antes del año 2000.
- EmployeeService.java y EmployeeServiceImpl.java: Se agregará un nuevo método para llamar al método del repositorio que se acaba de crear.
- EmployeeController.java: Se agregará un nuevo endpoint que llamará al nuevo método en el servicio.

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
package test.core.api.service;

import test.core.api.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeesBornBefore2000();
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

    @Override
    public List<Employee> getEmployeesBornBefore2000() {
        return employeeRepository.findEmployeesBornBefore2000();
    }
}
```

```java
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

    @GetMapping("/born-before-2000")
    public List<Employee> getEmployeesBornBefore2000() {
        return employeeService.getEmployeesBornBefore2000();
    }
}
```
