package hr.tvz.hrapp.domain.employee.service;

import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.employee.EmployeeDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface EmployeesService {

    Employee findById(Long id);

    List<EmployeeDTO> getAllEmployees();

    List<EmployeeDTO> getAllEmployeesExceptSelectedOne(Long id);
}
