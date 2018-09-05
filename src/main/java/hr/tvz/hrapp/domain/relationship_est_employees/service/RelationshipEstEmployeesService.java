package hr.tvz.hrapp.domain.relationship_est_employees.service;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployeesDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface RelationshipEstEmployeesService {

    RelationshipEstEmployeesDTO findAllForEvaluatorAndEstimation(Long estimationId, Long evaluatorId);

    List<RelationshipEstEmployeesDTO> findAllForEvaluator(Long evaluatorId);

    void save(RelationshipEstEmployeesDTO relationshipEstEmployeesDTO);

    void delete(Long estimationId, Long evaluatorId, Long evaluateeId);
}
