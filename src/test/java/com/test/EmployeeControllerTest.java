package com.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test; 
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import test.core.api.controller.EmployeeController;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void testGetAllEmployees() {
        List<Employee> mockList = List.of(new Employee(), new Employee());
        when(employeeService.getAllEmployees()).thenReturn(mockList);

        List<Employee> result = employeeController.getAll();

        assertEquals(2, result.size());
        verify(employeeService).getAllEmployees();
    }

    @Test
    void testInsertEmployee() {
        Employee input = new Employee();
        input.setFirstName("Rafael");
        input.setLastName("Moncada");

        Employee saved = new Employee();
        saved.setId(1L);
        saved.setFirstName("Rafael");
        saved.setLastName("Moncada");

        when(employeeService.saveEmployee(input)).thenReturn(saved);

        Employee result = employeeController.insert(input);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(employeeService).saveEmployee(input);
    }

    @Test
    void testDeleteEmployeeById() {
        Long id = 1L;

        ResponseEntity<Void> response = employeeController.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeService).deleteEmployeeById(id);
    }
}

