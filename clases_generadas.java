--- ANÁLISIS (Fase 1) ---
1. Clases que pueden reutilizarse directamente sin cambios:
   - Employee.java
   - EmployeeCoreApiApplication.java
2. Clases que deben modificarse (añadir métodos, anotaciones, endpoints, etc.):
   - EmployeeRepository.java
   - EmployeeController.java
   - EmployeeService.java
   - EmployeeServiceImpl.java
3. No se necesitan clases completamente nuevas.

--- PLAN (Fase 2) ---
- EmployeeRepository.java: Agregar un nuevo método para buscar empleados nacidos antes del año 2000.
- EmployeeController.java: Agregar un nuevo endpoint para obtener empleados nacidos antes del año 2000.
- EmployeeService.java: Agregar un nuevo método en la interfaz para obtener empleados nacidos antes del año 2000.
- EmployeeServiceImpl.java: Implementar el nuevo método de la interfaz EmployeeService.

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
    // Other methods...

    @Query("SELECT e FROM Employee e WHERE YEAR(e.birthDate) < 2000")
    List<Employee> findEmployeesBornBefore2000();
}
```

```java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    // Other fields...

    @Autowired
    private EmployeeRepository employeeRepository;

    // Other methods...

    @Override
    public List<Employee> findEmployeesBornBefore2000() {
        return employeeRepository.findEmployeesBornBefore2000();
    }
}
```
