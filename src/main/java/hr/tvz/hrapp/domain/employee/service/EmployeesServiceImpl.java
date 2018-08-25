package hr.tvz.hrapp.domain.employee.service;

import hr.tvz.hrapp.domain.BaseService;
import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.employee.repository.EmployeeRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class EmployeesServiceImpl extends BaseService implements EmployeesService {

    private final EmployeeRepository employeeRepository;

    public EmployeesServiceImpl(DozerBeanMapper dozerBeanMapper, EmployeeRepository employeeRepository) {
        super(dozerBeanMapper);
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return mapEntitiesToDTO(employees, EmployeeDTO.class);
    }
}
