package hr.tvz.hrapp.domain.employee.service;

import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.employee.mapper.EmployeeMapper;
import hr.tvz.hrapp.domain.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeesServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employeeMapper.mapListToDtoList(employees);
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesExceptSelectedOne(Long id) {
        List<Employee> employees = employeeRepository.findAllByIdNotIn(id);

        return employeeMapper.mapListToDtoList(employees);
    }
}
