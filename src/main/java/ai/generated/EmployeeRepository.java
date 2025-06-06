package test.core.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import test.core.api.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE YEAR(e.birthDate) < 2000")
    List<Employee> findEmployeesBornBefore2000();
}

// Fin de clase nueva: repositorio-base/src/main/java/test/core/api/repository/EmployeeRepository.java
