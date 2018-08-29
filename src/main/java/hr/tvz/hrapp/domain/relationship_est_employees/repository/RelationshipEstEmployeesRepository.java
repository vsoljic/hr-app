package hr.tvz.hrapp.domain.relationship_est_employees.repository;

import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipCompositeKey;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vedrana.soljic
 */
@Repository
public interface RelationshipEstEmployeesRepository extends JpaRepository<RelationshipEstEmployees, RelationshipCompositeKey> {

}
