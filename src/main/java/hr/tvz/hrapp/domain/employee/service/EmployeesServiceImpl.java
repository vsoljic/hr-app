package hr.tvz.hrapp.domain.employee.service;

import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.employee.mapper.EmployeeMapper;
import hr.tvz.hrapp.domain.employee.repository.EmployeeRepository;
import hr.tvz.hrapp.domain.estimation.Estimation;
import hr.tvz.hrapp.domain.estimation.repository.EstimationRepository;
import hr.tvz.hrapp.domain.estimation_evaluator.EstimationEvaluator;
import hr.tvz.hrapp.domain.estimation_evaluator.repository.EstimationEvaluatorRepository;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployeesDTO;
import hr.tvz.hrapp.domain.relationship_est_employees.service.RelationshipEstEmployeesService;
import hr.tvz.hrapp.service.UserService;
import hr.tvz.hrapp.service.dto.UserDTO;
import hr.tvz.hrapp.web.rest.errors.InternalServerErrorException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author vedrana.soljic
 */
@Service
public class EmployeesServiceImpl implements EmployeesService {

    private final EstimationRepository estimationRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final RelationshipEstEmployeesService relationshipEstEmployeesService;
    private final EstimationEvaluatorRepository estimationEvaluatorRepository;
    private final UserService userService;

    public EmployeesServiceImpl(EstimationRepository estimationRepository, EmployeeRepository employeeRepository, EmployeeMapper employeeMapper,
                                RelationshipEstEmployeesService relationshipEstEmployeesService, EstimationEvaluatorRepository estimationEvaluatorRepository, UserService userService) {
        this.estimationRepository = estimationRepository;
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.relationshipEstEmployeesService = relationshipEstEmployeesService;
        this.estimationEvaluatorRepository = estimationEvaluatorRepository;
        this.userService = userService;
    }

    @Override
    public EmployeeDTO findById(Long id) {
        return employeeMapper.mapToDto(employeeRepository.getOne(id));
    }


    @Override
    public List<EmployeeDTO> findAllByIds(List<Long> ids) {

        return employeeMapper.mapListToDtoList(employeeRepository.findAllById(ids));
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

    @Override
    public List<EmployeeDTO> getAllDistinctEvaluatorsForEstimation(List<Estimation> estimations) {

        List<Employee> employeesEvaluators = employeeRepository.findDistinctByEstimationsForEvaluator(estimations);
        return employeeMapper.mapListToDtoList(employeesEvaluators);
    }


    @Override
    public List<EmployeeDTO> findAllEvaluateesByEvaluatorAndEstimation(Long id, Long evaluatorId) {

        RelationshipEstEmployeesDTO relationship = relationshipEstEmployeesService.findAllForEmployeeAndEstimation(id, evaluatorId);

        List<EmployeeDTO> employeeEvaluatees = new ArrayList<>();
        if (relationship != null) {
            List<Long> evaluateeIds = relationship.getEvaluateeIdList();

            for (Long evaluateeId : evaluateeIds) {
                EmployeeDTO employeeDTO = findById(evaluateeId);
                employeeEvaluatees.add(employeeDTO);
            }
        }

        return employeeEvaluatees;
    }

    @Override
    public List<EmployeeDTO> findAllEvaluateesByEvaluatorLoggedInAndEst(Long estimationId) {
        return findAllEvaluateesByEvaluatorAndEstimation(estimationId, getEmployeeForLoggedInUser().getId());

    }

    @Override
    public EmployeeDTO getEmployeeForLoggedInUser() {
        UserDTO userDTO = userService.getUserWithAuthorities().map(UserDTO::new)
            .orElseThrow(() -> new InternalServerErrorException("User could not be found"));
        return findByUserId(userDTO.getId());
    }

    @Override
    public List<EmployeeDTO> findNotConnectedEvaluatorsForEstimation(Long estimationId) {
        List<EstimationEvaluator> estimationEvaluators = estimationEvaluatorRepository.findDistinctByKey_EstimationId(estimationId);
        List<Long> ids = new ArrayList<>();

        estimationEvaluators.stream().forEach(estimationEvaluator -> {
            ids.add(estimationEvaluator.getKey().getEvaluatorId());
        });

        List<EmployeeDTO> allEmployees = this.getAllEmployees();

        List<EmployeeDTO> filteredEmployees = allEmployees.stream().filter(employeeDTO ->
            !ids.contains(employeeDTO.getId()))
            .collect(Collectors.toList());

        return filteredEmployees;
    }

    @Override
    public List<EmployeeDTO> findNotConnectedEmployeesForEstimation(Long estimationId) {
        List<EmployeeDTO> allEmployees = this.getAllEmployees();

        //TODO:  mora biti bolji način da se radi s DTO????????
        Estimation estimation = estimationRepository.findById(estimationId).get();
        List<Employee> allEvaluatorsOnEstimation = estimation.getEmployeesEvaluators();

        List<Long> collectIdsOfEmployeesToRemove = allEvaluatorsOnEstimation.stream().map(Employee::getId).collect(Collectors.toList());

        List<EmployeeDTO> filtered = allEmployees.stream().filter(employeeDTO ->
            !collectIdsOfEmployeesToRemove.contains(employeeDTO.getId()))
            .collect(Collectors.toList());

        return filtered;

    }

    @Override
    public List<EmployeeDTO> findNotConnectedEmployeesForEvaluator(Long evaluatorId, Long estimationId) {

        List<EmployeeDTO> allEmployees = this.getAllEmployees();
        EmployeeDTO evaluator = this.findById(evaluatorId);
        List<EmployeeDTO> employeesToRemove = findAllEvaluateesByEvaluatorAndEstimation(estimationId, evaluatorId);
        employeesToRemove.add(evaluator);

        List<Long> collectIdsOfEmployeesToRemove = employeesToRemove.stream().map(EmployeeDTO::getId).collect(Collectors.toList());

        List<EmployeeDTO> filtered = allEmployees.stream().filter(employeeDTO ->
            !collectIdsOfEmployeesToRemove.contains(employeeDTO.getId()))
            .collect(Collectors.toList());

        return filtered;
    }

    @Override
    public void deleteSelectedEvaluatee(Long estimationId, Long evaluatorId, Long evaluateeId) {

        relationshipEstEmployeesService.delete(estimationId, evaluatorId, evaluateeId);
    }

    @Override
    public List<EmployeeDTO> findAllByEstimation(Long estimationId) {

        List<EstimationEvaluator> estimationEvaluators = estimationEvaluatorRepository.findDistinctByKey_EstimationId(estimationId);

        List<Long> evaluatorIds = new ArrayList<>();

        estimationEvaluators.stream().forEach(estimationEvaluator -> {
            evaluatorIds.add(estimationEvaluator.getKey().getEvaluatorId());
        });

        List<EmployeeDTO> evaluators = employeeMapper.mapListToDtoList(employeeRepository.findAllById(evaluatorIds));

        return evaluators;

    }
}
