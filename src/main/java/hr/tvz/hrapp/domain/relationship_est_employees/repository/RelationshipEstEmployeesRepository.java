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

    RelationshipEstEmployees findByRelationshipCompositeKey_EstimationIdAndAndRelationshipCompositeKey_EmployeeEvaluatorIdAndRelationshipCompositeKey_EmployeeEvaluateeId(Long estimationId, Long evaluatorId, Long evaluateeId);

    List<RelationshipEstEmployees> findAllByRelationshipCompositeKey_EstimationIdAndAndRelationshipCompositeKey_EmployeeEvaluatorId(Long estimationId, Long evaluatorId);

    // List<RelationshipEstEmployees> findDistinctByRelationshipCompositeKey_EmployeeEvaluatorId(Long evaluatorId);

}
