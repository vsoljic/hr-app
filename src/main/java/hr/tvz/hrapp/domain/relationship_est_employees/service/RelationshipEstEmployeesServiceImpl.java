package hr.tvz.hrapp.domain.relationship_est_employees.service;

import hr.tvz.hrapp.domain.employee.mapper.EmployeeMapper;
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
            .findAllByEstimationIdAndEmployeeEvaluatorId(estimationId, evaluatorId);

        return mapper.mapToDto(relationshipEstEmployeesList);
    }

    @Override
    public List<RelationshipEstEmployeesDTO> findAllForEvaluator(Long evaluatorId) {
        List<RelationshipEstEmployees> relationshipEstEmployeesList = relationshipEstEmployeesRepository
            .findDistinctByEmployeeEvaluatorId(evaluatorId);

        return mapper.mapListToDtoList(relationshipEstEmployeesList, evaluatorId);
    }

    @Override
    public void save(RelationshipEstEmployeesDTO relationshipEstEmployeesDTO) {
        List<RelationshipEstEmployees> relationshipEstEmployeesList = new ArrayList<>();

        for (Long evaluateeId : relationshipEstEmployeesDTO.getEvaluateeIdList()) {
            relationshipEstEmployeesList.add(new RelationshipEstEmployees(relationshipEstEmployeesDTO.getEstimationId(), relationshipEstEmployeesDTO.getEvaluatorId(), evaluateeId));
        }

        relationshipEstEmployeesRepository.saveAll(relationshipEstEmployeesList);


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
        // all relationships on esitmation
        List<RelationshipEstEmployees> relationshipsOnEstimation = relationshipEstEmployeesRepository.findAllByEstimationId(estimationId);

        // relationship that we want to delete
        RelationshipEstEmployees relationship = relationshipEstEmployeesRepository.
            findAllByEstimationIdAndEmployeeEvaluatorIdAndEmployeeEvaluateeId(estimationId, evaluatorId, evaluateeId);

        // if only one is left, update evaluatee_id to 0 (so we don't loose evaluator)
        if (relationshipsOnEstimation.size() == 1) {

            // insert fake one
            RelationshipEstEmployees relationshipToUpdate = new RelationshipEstEmployees(estimationId, evaluatorId);
            relationshipEstEmployeesRepository.save(relationshipToUpdate);

            // delete selected one
            relationshipEstEmployeesRepository.delete(relationship);

        } else {
            relationshipEstEmployeesRepository.delete(relationship);
        }

    }
}
