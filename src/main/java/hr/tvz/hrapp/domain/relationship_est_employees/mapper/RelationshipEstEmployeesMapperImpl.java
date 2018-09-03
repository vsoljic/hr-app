package hr.tvz.hrapp.domain.relationship_est_employees.mapper;

import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipCompositeKey;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployees;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployeesDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vedrana.soljic
 */
@Component
public class RelationshipEstEmployeesMapperImpl implements RelationshipEstEmployeesMapper {

    @Override
    public RelationshipEstEmployeesDTO mapToDto(List<RelationshipEstEmployees> relationships) {

        RelationshipEstEmployeesDTO dto = new RelationshipEstEmployeesDTO();
        ArrayList<Long> evaluateesIds = new ArrayList<>();

        for (RelationshipEstEmployees r : relationships) {
          evaluateesIds.add(r.getRelationshipCompositeKey().getEmployeeEvaluateeId());
        }

        dto.setEvaluatorId(relationships.get(0).getRelationshipCompositeKey().getEmployeeEvaluatorId());
        dto.setEstimationId(relationships.get(0).getRelationshipCompositeKey().getEstimationId());
        dto.setEvaluateeIdList(evaluateesIds);


        return dto;
    }

    @Override
    public List<RelationshipEstEmployees> reverse(RelationshipEstEmployeesDTO relationshipDTO) {

        List<RelationshipEstEmployees> relationships = new ArrayList<>();

        for (Long id : relationshipDTO.getEvaluateeIdList()) {
            RelationshipEstEmployees relationship = new RelationshipEstEmployees();
            RelationshipCompositeKey key = new RelationshipCompositeKey();

            key.setEstimationId(relationshipDTO.getEstimationId());
            key.setEmployeeEvaluatorId(relationshipDTO.getEvaluatorId());
            key.setEmployeeEvaluateeId(id);

            relationships.add(relationship);
        }

        return relationships;
    }

}
