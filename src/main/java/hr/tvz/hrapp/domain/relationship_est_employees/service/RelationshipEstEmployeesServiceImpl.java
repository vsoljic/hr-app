package hr.tvz.hrapp.domain.relationship_est_employees.service;

import hr.tvz.hrapp.domain.BaseService;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipCompositeKey;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployees;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployeesDTO;
import hr.tvz.hrapp.domain.relationship_est_employees.repository.RelationshipEstEmployeesRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class RelationshipEstEmployeesServiceImpl extends BaseService implements RelationshipEstEmployeesService {

    private final RelationshipEstEmployeesRepository relationshipEstEmployeesRepository;

    public RelationshipEstEmployeesServiceImpl(DozerBeanMapper dozerBeanMapper,
                                               RelationshipEstEmployeesRepository relationshipEstEmployeesRepository) {
        super(dozerBeanMapper);
        this.relationshipEstEmployeesRepository = relationshipEstEmployeesRepository;
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
}
