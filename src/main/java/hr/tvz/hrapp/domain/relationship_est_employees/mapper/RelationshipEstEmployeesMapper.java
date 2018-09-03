package hr.tvz.hrapp.domain.relationship_est_employees.mapper;

import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployees;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployeesDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */

public interface RelationshipEstEmployeesMapper {

    RelationshipEstEmployeesDTO mapToDto(List<RelationshipEstEmployees> relationships);

    List<RelationshipEstEmployees> reverse(RelationshipEstEmployeesDTO relationshipDTO);

}
