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

