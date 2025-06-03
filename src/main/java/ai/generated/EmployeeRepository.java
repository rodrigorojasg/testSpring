package test.core.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.core.api.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE MONTH(e.birthDate) = MONTH(CURRENT_DATE)")
    List<Employee> findEmployeesWithBirthdayThisMonth();
}

