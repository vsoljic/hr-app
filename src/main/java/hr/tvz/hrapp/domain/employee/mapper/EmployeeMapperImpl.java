package hr.tvz.hrapp.domain.employee.mapper;

import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vedrana.soljic
 */
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDTO mapToDto(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmployeeCode(employee.getEmployeeCode());
        employeeDTO.setUser(employee.getUser());
        employeeDTO.setWorkPosition(employee.getWorkPosition());

        return employeeDTO;
    }

    @Override
    public Employee reverse(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmployeeCode(employeeDTO.getEmployeeCode());
        employee.setUser(employeeDTO.getUser());
        employee.setWorkPosition(employeeDTO.getWorkPosition());

        return employee;
    }

    @Override
    public List<EmployeeDTO> mapListToDtoList(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employees.stream().forEach(i -> employeeDTOS.add(mapToDto(i)));

        return employeeDTOS;
    }

    @Override
    public List<Employee> mapDtoListToList(List<EmployeeDTO> employeeDTOS) {
        List<Employee> employees = new ArrayList<>();
        employeeDTOS.stream().forEach(i -> employees.add(reverse(i)));

        return employees;
    }


}
