package hr.tvz.hrapp.domain.employee.repository;

import hr.tvz.hrapp.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vedrana.soljic
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
