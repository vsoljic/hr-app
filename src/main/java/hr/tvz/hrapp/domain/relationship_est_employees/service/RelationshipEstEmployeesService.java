package hr.tvz.hrapp.domain.relationship_est_employees.service;

import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployeesDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface RelationshipEstEmployeesService {

    RelationshipEstEmployeesDTO findAllForEmployeeAndEstimation(Long estimationId, Long evaluatorId);

    List<RelationshipEstEmployeesDTO> findAllForEvaluatee(Long evaluateeId);

    List<RelationshipEstEmployeesDTO> findAllForEvaluator(Long evaluatorId);

    void save(RelationshipEstEmployeesDTO relationshipEstEmployeesDTO);

    void saveForEvaluator(List<RelationshipEstEmployeesDTO> relationshipEstEmployeesDTOS);

    void delete(Long estimationId, Long evaluatorId, Long evaluateeId);
}
