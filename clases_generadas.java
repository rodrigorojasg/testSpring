--- CLASES MODIFICADAS ---

// Inicio de clase modificada: repositorio-base/src/main/java/test/core/api/controller/EmployeeController.java

package test.core.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> getAll() {
        System.out.println("Fetching all employees");
        return service.getAllEmployees();
    }

    @GetMapping("/bornBefore2000")
    public List<Employee> getEmployeesBornBefore2000() {
        System.out.println("Fetching employees born before 2000");
        return service.getEmployeesBornBefore2000();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public Employee insert(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }
}

// Fin de clase modificada: repositorio-base/src/main/java/test/core/api/controller/EmployeeController.java


// Inicio de clase modificada: repositorio-base/src/main/java/test/core/api/service/impl/EmployeeServiceImpl.java

package test.core.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.EmployeeService;

@Component
public class EmployeeServiceImpl implements EmployeeService {
	
	    @Autowired
	    private EmployeeRepository repository;

	    public List<Employee> getAllEmployees() {
	        return (List<Employee>) repository.findAll();
	    }

	    public List<Employee> getEmployeesBornBefore2000() {
	        return repository.findEmployeesBornBefore2000();
	    }

	    public void deleteEmployeeById(Long id) {
	        repository.deleteById(id);
	    }

	    public Employee saveEmployee(Employee employee) {
	        return repository.save(employee); 
	    }
}

// Fin de clase modificada: repositorio-base/src/main/java/test/core/api/service/impl/EmployeeServiceImpl.java

--- CLASES NUEVAS ---

// Inicio de clase nueva: repositorio-base/src/main/java/test/core/api/repository/EmployeeRepository.java

package test.core.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import test.core.api.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE YEAR(e.birthDate) < 2000")
    List<Employee> findEmployeesBornBefore2000();
}

// Fin de clase nueva: repositorio-base/src/main/java/test/core/api/repository/EmployeeRepository.java
