package hr.tvz.hrapp.domain.estimation.service;

import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.employee.mapper.EmployeeMapper;
import hr.tvz.hrapp.domain.employee.service.EmployeesService;
import hr.tvz.hrapp.domain.estimation.Estimation;
import hr.tvz.hrapp.domain.estimation.EstimationDTO;
import hr.tvz.hrapp.domain.estimation.mapper.EstimationMapper;
import hr.tvz.hrapp.domain.estimation.repository.EstimationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author vedrana.soljic
 */
@Service
public class EstimationServiceImpl implements EstimationService {

    private final EstimationRepository estimationRepository;

    private final EmployeesService employeesService;

    private final EstimationMapper estimationMapper;

    private final EmployeeMapper employeeMapper;

    public EstimationServiceImpl(EstimationRepository estimationRepository, EmployeesService employeesService,
                                 EstimationMapper estimationMapper, EmployeeMapper employeeMapper) {
        this.estimationRepository = estimationRepository;
        this.employeesService = employeesService;
        this.estimationMapper = estimationMapper;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EstimationDTO> findAllEstimations() {
        return estimationMapper.mapListToDtoList(estimationRepository.findAllByActivityNot(0));
    }

    @Override
    public EstimationDTO findById(Long id) {
        return estimationMapper.mapToDto(estimationRepository.findById(id).get());
    }

    @Override
    public List<EmployeeDTO> findAllEmployeesEvaluatorsById(Long id) {

        Estimation estimation = estimationRepository.findById(id).get();
        List<Employee> employeeEvaluators = estimation.getEmployeesEvaluators();

        return employeeMapper.mapListToDtoList(employeeEvaluators);
    }

    @Override
    public List<EmployeeDTO> findAllEvaluateesByEvaluatorAndEstimation(Long id, Long evaluatorId) {

        Estimation estimation = estimationRepository.findById(id).get();
        List<Employee> employeeEvaluatees = estimation.getEmployeesEvaluatees()
            .stream().filter(employee -> employee.getId().equals(evaluatorId)).collect(Collectors.toList());


        return employeeMapper.mapListToDtoList(employeeEvaluatees);
    }

    @Override
    public EstimationDTO createNewEstimation(EstimationDTO estimationDTO) {

        Estimation estimation = estimationMapper.reverse(estimationDTO);
        estimation.setActivity(1);

        Estimation newEstimation = estimationRepository.save(estimation);

        return estimationMapper.mapToDto(newEstimation);
    }

    @Override
    public EstimationDTO saveSelectedEstimation(EstimationDTO estimationDTO) {
        Estimation estimation = estimationRepository.findById(estimationDTO.getId()).get();

        estimation.setModel(estimationDTO.getModel());
        estimation.setStatus(estimationDTO.getStatus());
        estimation.setName(estimationDTO.getName());
        estimation.setPeriodFrom(estimationDTO.getPeriodFrom());
        estimation.setPeriodTo(estimationDTO.getPeriodTo());

  /*      estimation.getEmployeesEvaluatees().clear();
        estimation.getEmployeesEvaluators().clear();


        estimation.setEmployeesEvaluatees(getEmployeesByDtoIds(estimationDTO.getEmployeesEvaluatees()));
        estimation.setEmployeesEvaluators(getEmployeesByDtoIds(estimationDTO.getEmployeesEvaluators()));

*/
        estimation = estimationRepository.save(estimation);

        return estimationMapper.mapToDto(estimation);
    }

    private List<Employee> getEmployeesByDtoIds(List<EmployeeDTO> employeeDtos) {
        List<Employee> result = new ArrayList<>();
        employeeDtos.stream().forEach(i -> result.add(employeesService.findById(i.getId())));

        return result;
    }


}
