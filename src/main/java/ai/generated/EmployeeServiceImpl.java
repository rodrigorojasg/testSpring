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

