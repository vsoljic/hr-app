package hr.tvz.hrapp.domain.employee.service;

import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.employee.mapper.EmployeeMapper;
import hr.tvz.hrapp.domain.employee.repository.EmployeeRepository;
import hr.tvz.hrapp.domain.estimation.Estimation;
import hr.tvz.hrapp.domain.estimation.repository.EstimationRepository;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployeesDTO;
import hr.tvz.hrapp.domain.relationship_est_employees.service.RelationshipEstEmployeesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author vedrana.soljic
 */
@Service
public class EmployeesServiceImpl implements EmployeesService {

    //TODO: Repository umjesto servisa - zato što preko servisa ne može zbog dva mappera - circular reference
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final RelationshipEstEmployeesService relationshipEstEmployeesService;

    public EmployeesServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper,
                                RelationshipEstEmployeesService relationshipEstEmployeesService) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.relationshipEstEmployeesService = relationshipEstEmployeesService;
    }

    @Override
    public EmployeeDTO findById(Long id) {
        return employeeMapper.mapToDto(employeeRepository.getOne(id));
    }

    @Override
    public EmployeeDTO findByUserId(Long userId) {
        return employeeMapper.mapToDto(employeeRepository.findByUser_Id(userId));
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

    public List<EmployeeDTO> getAllDistinctEvaluatorsForEstimation(List<Estimation> estimations) {

        List<Employee> employeesEvaluators = employeeRepository.findDistinctByEstimationsForEvaluator(estimations);
        return employeeMapper.mapListToDtoList(employeesEvaluators);
    }


    @Override
    public List<EmployeeDTO> findAllEvaluateesByEvaluatorAndEstimation(Long id, Long evaluatorId) {

        RelationshipEstEmployeesDTO relationship = relationshipEstEmployeesService.findAllForEvaluatorAndEstimation(id, evaluatorId);

        List<Long> evaluateeIds = relationship.getEvaluateeIdList();
        List<EmployeeDTO> employeeEvaluatees = new ArrayList<>();

        for (Long evaluateeId : evaluateeIds) {
            EmployeeDTO employeeDTO = findById(evaluateeId);
            employeeEvaluatees.add(employeeDTO);
        }

        return employeeEvaluatees;
    }

    @Override
    public List<EmployeeDTO> findNotConnectedEmployeesForEvaluator(Long evaluatorId, Long estimationId) {

        List<EmployeeDTO> allEmployees = this.getAllEmployees();
        EmployeeDTO evaluator= this.findById(evaluatorId);
        List<EmployeeDTO> evaluatees = findAllEvaluateesByEvaluatorAndEstimation(estimationId, evaluatorId);
        evaluatees.add(evaluator);

        List<Long> collect = evaluatees.stream().map(EmployeeDTO::getId).collect(Collectors.toList());

        List<EmployeeDTO> filtered = allEmployees.stream().filter(employeeDTO -> !collect.contains(employeeDTO.getId())).collect(Collectors.toList());
//        List<EmployeeDTO> employeesWOEvaluator = allEmployees.stream()
//            .filter(e -> evaluatees.contains(e))
//            .collect(Collectors.toList());

      //  List<EmployeeDTO> employeesWOEvaluator = allEmployees.stream().filter(evaluatees::contains).collect(Collectors.toList());

       /* List<EmployeeDTO> notConnectedEmployees = new ArrayList<>();

        for (EmployeeDTO emp : employeesWOEvaluator) {
           for (EmployeeDTO e : evaluatees) {
               if (!e.getId().equals(emp.getId())) {
                   notConnectedEmployees.add(emp);
               }
           }
        }
*/
        return filtered;
    }

    @Override
    public void deleteSelectedEvaluatee(Long estimationId, Long evaluatorId, Long evaluateeId) {

        relationshipEstEmployeesService.delete(estimationId, evaluatorId, evaluateeId);
    }
}
