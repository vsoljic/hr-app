package hr.tvz.hrapp.domain.relationship_est_employees.repository;

import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipCompositeKey;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Repository
public interface RelationshipEstEmployeesRepository extends JpaRepository<RelationshipEstEmployees, RelationshipCompositeKey> {

    RelationshipEstEmployees findAllByEstimationIdAndEmployeeEvaluatorIdAndEmployeeEvaluateeId(Long estimationId, Long evaluatorId, Long evaluateeId);

    List<RelationshipEstEmployees> findAllByEstimationIdAndEmployeeEvaluatorId(Long estimationId, Long evaluatorId);

    List<RelationshipEstEmployees> findDistinctByEmployeeEvaluatorId(Long evaluatorId);

    List<RelationshipEstEmployees> findAllByEstimationId(Long estimationId);

}
