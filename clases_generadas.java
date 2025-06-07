--- CLASE MODIFICADA ---

package test.core.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployeeById(Long id) {
        // Start of AI modification
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null && "Femenino".equals(employee.getGender())) {
            throw new CannotDeleteEmployeeException("Cannot delete female employee with id: " + id);
        }
        // End of AI modification
        employeeRepository.deleteById(id);
    }

    // Other existing methods...
}

--- NUEVA CLASE ---

package test.core.api.exception;

public class CannotDeleteEmployeeException extends RuntimeException {
    public CannotDeleteEmployeeException(String message) {
        super(message);
    }
}

--- TEST UNITARIO NUEVO ---

package test.core.api.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void deleteEmployeeById_FemaleEmployee_ThrowsException() {
        Employee femaleEmployee = new Employee();
        femaleEmployee.setGender("Femenino");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(femaleEmployee));

        assertThrows(CannotDeleteEmployeeException.class, () -> employeeService.deleteEmployeeById(1L));
    }

    @Test
    public void deleteEmployeeById_MaleEmployee_AllowsDeletion() {
        Employee maleEmployee = new Employee();
        maleEmployee.setGender("Masculino");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(maleEmployee));

        employeeService.deleteEmployeeById(1L);  // No exception should be thrown
    }
}
