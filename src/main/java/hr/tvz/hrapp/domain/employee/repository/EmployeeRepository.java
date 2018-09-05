package hr.tvz.hrapp.domain.employee.repository;

import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.estimation.Estimation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByIdNotIn(Long id);

    Employee findByUser_Id(Long userId);

    List<Employee> findDistinctByEstimationsForEvaluator(List<Estimation> estimations);
}
