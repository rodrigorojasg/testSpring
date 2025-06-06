package com.example.demo.service;

import com.example.demo.exception.GenderException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        
        // Start of AI modification
        if(employee.getGender().equalsIgnoreCase("Femenino")) {
            throw new GenderException("Cannot delete employee of gender 'Femenino'");
        }
        // End of AI modification

        employeeRepository.delete(employee);
    }
}
package com.example.demo.exception;

public class GenderException extends RuntimeException {
    public GenderException(String message) {
        super(message);
    }
}
package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String gender;

    // getters and setters
}
package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
package com.example.demo.service;

import com.example.demo.exception.GenderException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void deleteEmployee_GenderFemenino_ThrowsGenderException() {
        Employee employee = new Employee();
        employee.setGender("Femenino");

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        assertThrows(GenderException.class, () -> employeeService.deleteEmployee(1L));
    }

    @Test
    public void deleteEmployee_GenderMasculino_DeletesSuccessfully() {
        Employee employee = new Employee();
        employee.setGender("Masculino");

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);
        Mockito.verify(employeeRepository, Mockito.times(1)).delete(employee);
    }
}
