package hr.tvz.hrapp.domain.employee.mapper;

import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.employee.EmployeeDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface EmployeeMapper {

    EmployeeDTO mapToDto(Employee employee);

    Employee reverse(EmployeeDTO employeeDTO);

    List<EmployeeDTO> mapListToDtoList(List<Employee> employees);

    List<Employee> mapDtoListToList(List<EmployeeDTO> employeeDTOS);

}
