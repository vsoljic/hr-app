package hr.tvz.hrapp.domain.employee.service;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface EmployeesService {

    List<EmployeeDTO> getAllEmployees();
}
