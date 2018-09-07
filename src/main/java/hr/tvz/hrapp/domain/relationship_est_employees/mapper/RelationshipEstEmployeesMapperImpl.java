package hr.tvz.hrapp.domain.relationship_est_employees.mapper;

import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipCompositeKey;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployees;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployeesDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<RelationshipEstEmployeesDTO> mapListToDtoList(List<RelationshipEstEmployees> relationships, Long id) {
        List<RelationshipEstEmployeesDTO> result = new ArrayList<>();
        Map<Long, List<Long>> groupedEstimations = new HashMap<>();

        relationships.stream().forEach(relationshipEstEmployees -> {
            Long key = relationshipEstEmployees.getRelationshipCompositeKey().getEstimationId();

            if (groupedEstimations.containsKey(key)) {
                List<Long> list = groupedEstimations.get(key);
                list.add(relationshipEstEmployees.getRelationshipCompositeKey().getEmployeeEvaluateeId());
            } else {
                List<Long> list = new ArrayList<>();
                list.add(relationshipEstEmployees.getRelationshipCompositeKey().getEmployeeEvaluateeId());
                groupedEstimations.put(key, list);
            }
        });

        groupedEstimations.forEach((aLong, longs) -> result.add(new RelationshipEstEmployeesDTO(aLong, id, longs)));

        return result;
    }

    @Override
    public List<RelationshipEstEmployees> mapDtoListToList(List<RelationshipEstEmployeesDTO> dtos) {
        List<RelationshipEstEmployees> result = new ArrayList<>();
        /*  dtos.stream().forEach(i -> result.add(reverse(i)));*/
        // TODO: NAPRAVITI OVO!!!!!!!!!!!!!!!
        return result;
    }
}
