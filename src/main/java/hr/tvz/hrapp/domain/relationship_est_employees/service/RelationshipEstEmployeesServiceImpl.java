package hr.tvz.hrapp.domain.relationship_est_employees.service;

import hr.tvz.hrapp.domain.employee.mapper.EmployeeMapper;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipCompositeKey;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployees;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployeesDTO;
import hr.tvz.hrapp.domain.relationship_est_employees.mapper.RelationshipEstEmployeesMapper;
import hr.tvz.hrapp.domain.relationship_est_employees.repository.RelationshipEstEmployeesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class RelationshipEstEmployeesServiceImpl implements RelationshipEstEmployeesService {

    private final RelationshipEstEmployeesRepository relationshipEstEmployeesRepository;

    private final RelationshipEstEmployeesMapper mapper;

    public RelationshipEstEmployeesServiceImpl(RelationshipEstEmployeesRepository relationshipEstEmployeesRepository, EmployeeMapper employeeMapper, RelationshipEstEmployeesMapper mapper) {

        this.relationshipEstEmployeesRepository = relationshipEstEmployeesRepository;
        this.mapper = mapper;
    }

    public RelationshipEstEmployeesDTO findAllForEvaluatorAndEstimation(Long estimationId, Long evaluatorId) {
        List<RelationshipEstEmployees> relationshipEstEmployeesList = relationshipEstEmployeesRepository
            .findAllByRelationshipCompositeKey_EstimationIdAndAndRelationshipCompositeKey_EmployeeEvaluatorId(estimationId, evaluatorId);

        return mapper.mapToDto(relationshipEstEmployeesList);
    }

    @Override
    public List<RelationshipEstEmployeesDTO> findAllForEvaluator(Long evaluatorId) {
        List<RelationshipEstEmployees> relationshipEstEmployeesList = relationshipEstEmployeesRepository
            .findDistinctByRelationshipCompositeKey_EmployeeEvaluatorId(evaluatorId);

        return mapper.mapListToDtoList(relationshipEstEmployeesList, evaluatorId);
    }

    @Override
    public void save(RelationshipEstEmployeesDTO relationshipEstEmployeesDTO) {

        List<RelationshipEstEmployees> relationshipEstEmployeesList = new ArrayList<>();

        for (Long evaluateeId : relationshipEstEmployeesDTO.getEvaluateeIdList()) {
            RelationshipCompositeKey relationshipCompositeKey = new RelationshipCompositeKey();
            relationshipCompositeKey.setEstimationId(relationshipEstEmployeesDTO.getEstimationId());
            relationshipCompositeKey.setEmployeeEvaluatorId(relationshipEstEmployeesDTO.getEvaluatorId());
            relationshipCompositeKey.setEmployeeEvaluateeId(evaluateeId);

            relationshipEstEmployeesList.add(new RelationshipEstEmployees(relationshipCompositeKey));
        }

        for (RelationshipEstEmployees relationship : relationshipEstEmployeesList) {
            relationshipEstEmployeesRepository.save(relationship);
        }

    }

    @Override
    public void save(List<RelationshipEstEmployeesDTO> relationshipEstEmployeesDTOS) {
        List<RelationshipEstEmployees> relationshipEstEmployeesList = mapper.mapDtoListToList(relationshipEstEmployeesDTOS);

        for (RelationshipEstEmployees relationship : relationshipEstEmployeesList) {
            relationshipEstEmployeesRepository.save(relationship);
        }

    }

    @Override
    public void delete(Long estimationId, Long evaluatorId, Long evaluateeId) {
        RelationshipEstEmployees relationship = relationshipEstEmployeesRepository.
            findByRelationshipCompositeKey_EstimationIdAndAndRelationshipCompositeKey_EmployeeEvaluatorIdAndRelationshipCompositeKey_EmployeeEvaluateeId(estimationId, evaluatorId, evaluateeId);

        relationshipEstEmployeesRepository.delete(relationship);
    }
}
