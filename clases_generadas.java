package com.example.demo.service;

import com.example.demo.exception.GenderValidationException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void validateAndDeleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        // Start of AI modification
        if (employee != null && "Femenino".equals(employee.getGender())) {
            throw new GenderValidationException("Cannot delete employee with gender 'Femenino'");
        }
        // End of AI modification

        employeeRepository.deleteById(id);
    }
}

package com.example.demo.controller;

import com.example.demo.exception.GenderValidationException;
import com.example.demo.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            employeeService.validateAndDeleteEmployeeById(id);
            return ResponseEntity.ok().build();
        } catch (GenderValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.example.demo.exception;

public class GenderValidationException extends RuntimeException {
    public GenderValidationException(String message) {
        super(message);
    }
}

package com.example.demo.service;

import com.example.demo.exception.GenderValidationException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void validateAndDeleteEmployeeById_GenderFemenino_ThrowsException() {
        Employee employee = new Employee();
        employee.setGender("Femenino");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        assertThrows(GenderValidationException.class, () -> {
            employeeService.validateAndDeleteEmployeeById(1L);
        });

        verify(employeeRepository, Mockito.never()).deleteById(1L);
    }

    @Test
    public void validateAndDeleteEmployeeById_GenderMasculino_DeletesEmployee() {
        Employee employee = new Employee();
        employee.setGender("Masculino");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.validateAndDeleteEmployeeById(1L);

        verify(employeeRepository).deleteById(1L);
    }
}
