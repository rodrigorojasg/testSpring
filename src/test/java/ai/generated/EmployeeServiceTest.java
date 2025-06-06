
package test.core.api.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.impl.EmployeeServiceImpl;



@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;


    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testDeleteFemaleEmployee() {

        MockitoAnnotations.initMocks(this);
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setGender("Femenino");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        assertThrows(IllegalArgumentException.class, () -> employeeService.deleteEmployeeById(1L));
    }

    @Test
    public void testDeleteNonExistingEmployee() {
        MockitoAnnotations.initMocks(this);
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> employeeService.deleteEmployeeById(1L));

        Employee femaleEmployee = new Employee();
        femaleEmployee.setId(1L);
        femaleEmployee.setGender("Femenino");
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(femaleEmployee));

        assertThrows(UnsupportedOperationException.class, () -> employeeService.deleteEmployee(1L));
        verify(employeeRepository, never()).delete(femaleEmployee);
    }

    @Test
    public void testDeleteMaleEmployee() {
        Employee maleEmployee = new Employee();
        maleEmployee.setId(2L);
        maleEmployee.setGender("Masculino");
        when(employeeRepository.findById(2L)).thenReturn(java.util.Optional.of(maleEmployee));

        employeeService.deleteEmployee(2L);
        verify(employeeRepository, times(1)).delete(maleEmployee);

    }
}
