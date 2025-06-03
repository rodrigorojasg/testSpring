package test.core.api.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.impl.EmployeeServiceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetEmployeesBornBeforeYear() {
        Employee employee1 = new Employee();
        employee1.setBirthDate(LocalDate.of(1990, 1, 1));

        Employee employee2 = new Employee();
        employee2.setBirthDate(LocalDate.of(1985, 1, 1));

        Mockito.when(employeeRepository.findEmployeesBornBeforeYear(LocalDate.of(2000, 1, 1)))
                .thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> employees = employeeService.getEmployeesBornBeforeYear(2000);

        assertEquals(2, employees.size());
        assertEquals(employee1, employees.get(0));
        assertEquals(employee2, employees.get(1));
    }
}
