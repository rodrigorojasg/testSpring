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

	    public void deleteEmployeeById(Long id) {
	        Employee employee = repository.findById(id).orElse(null);
	        if(employee != null) {
	            // Start of AI modification for HDU-EMP-003
	            if("Femenino".equals(employee.getGender())) {
	                throw new IllegalArgumentException("Cannot delete female employee due to internal policy");
	            }
	            // End of AI modification for HDU-EMP-003
	            repository.deleteById(id);
	        } else {
	            throw new IllegalArgumentException("Employee with id " + id + " does not exist");
	        }
	    }

	    public Employee saveEmployee(Employee employee) {
	        return repository.save(employee); 
	    }
}


// Archivo: src/test/java/test/core/api/service/EmployeeServiceTest.java

