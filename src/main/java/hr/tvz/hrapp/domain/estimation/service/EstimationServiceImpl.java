package hr.tvz.hrapp.domain.estimation.service;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.employee.service.EmployeesService;
import hr.tvz.hrapp.domain.estimation.Estimation;
import hr.tvz.hrapp.domain.estimation.EstimationDTO;
import hr.tvz.hrapp.domain.estimation.mapper.EstimationMapper;
import hr.tvz.hrapp.domain.estimation.repository.EstimationRepository;
import hr.tvz.hrapp.domain.estimation_evaluator.EstimationEvaluator;
import hr.tvz.hrapp.domain.estimation_evaluator.service.EstimationEvaluatorService;
import hr.tvz.hrapp.domain.estimation_model.mapper.EstimationModelMapper;
import hr.tvz.hrapp.domain.estimation_status.mapper.EstimationStatusMapper;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployees;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployeesDTO;
import hr.tvz.hrapp.domain.relationship_est_employees.service.RelationshipEstEmployeesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class EstimationServiceImpl implements EstimationService {

    private final EstimationRepository estimationRepository;
    private final EmployeesService employeesService;
    private final RelationshipEstEmployeesService relationshipEstEmployeesService;
    private final EstimationMapper estimationMapper;
    private final EstimationModelMapper estimationModelMapper;
    private final EstimationStatusMapper estimationStatusMapper;
    private final EstimationEvaluatorService estimationEvaluatorService;

    public EstimationServiceImpl(EstimationRepository estimationRepository, EmployeesService employeesService,
                                 RelationshipEstEmployeesService relationshipEstEmployeesService,
                                 EstimationMapper estimationMapper, EstimationModelMapper estimationModelMapper,
                                 EstimationStatusMapper estimationStatusMapper, EstimationEvaluatorService estimationEvaluatorService) {
        this.estimationRepository = estimationRepository;
        this.employeesService = employeesService;
        this.relationshipEstEmployeesService = relationshipEstEmployeesService;
        this.estimationMapper = estimationMapper;
        this.estimationModelMapper = estimationModelMapper;
        this.estimationStatusMapper = estimationStatusMapper;
        this.estimationEvaluatorService = estimationEvaluatorService;
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
    public List<EstimationDTO> findAllByLoggedInUserEvaluator(Long id) {

        List<EstimationEvaluator> estimationEvaluators = estimationEvaluatorService.getAllForEvaluatorId(id);

        List<EstimationDTO> estimationsDtos = new ArrayList<>();
        estimationEvaluators.stream().forEach(estimationEvaluator -> estimationsDtos.add(
            estimationMapper.mapToDto(estimationRepository.findById(estimationEvaluator.getKey().getEstimationId()).get())
        ));

        return estimationsDtos;
    }

    @Override
    public List<EstimationDTO> findAllByLoggedInUserEvaluatee(Long id) {

        List<RelationshipEstEmployeesDTO> relationshipEstEmployees = relationshipEstEmployeesService.findAllForEvaluatee(id);

        List<EstimationDTO> estimationDTOS = new ArrayList<>();

        relationshipEstEmployees.forEach(relationship ->
            estimationDTOS.add(estimationMapper.mapToDto(estimationRepository.findById(relationship.getEstimationId()).get())));

        return estimationDTOS;
    }

    @Override
    public List<EmployeeDTO> findAllEmployeesEvaluatorsByEstimationId(Long id) {

        Estimation estimation = estimationRepository.findById(id).get();
        /*        List<Employee> employeeEvaluators = estimation.getEmployeesEvaluators();*/

        List<Estimation> estimations = new ArrayList<>();
        estimations.add(estimation);

        List<EmployeeDTO> employeeEvaluators = employeesService.getAllDistinctEvaluatorsForEstimation(estimations);

        return employeeEvaluators;
    }

    @Override
    public EstimationDTO createNewEstimation(EstimationDTO estimationDTO) {

        Estimation estimation = estimationMapper.reverse(estimationDTO);
        estimation.setActivity(1); //TODO: promjeniti logiku za activity procjene

        Estimation newEstimation = estimationRepository.save(estimation);
        return estimationMapper.mapToDto(newEstimation);
    }

    @Override
    public EstimationDTO saveSelectedEstimation(EstimationDTO estimationDTO) {
        Estimation estimation = estimationRepository.findById(estimationDTO.getId()).get();

        estimation.setEstimationModel(estimationModelMapper.reverse(estimationDTO.getModel()));
        estimation.setStatus(estimationStatusMapper.reverse(estimationDTO.getStatus()));
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

   /* private List<Employee> getEmployeesByDtoIds(List<EmployeeDTO> employeeDtos) {
        List<Employee> result = new ArrayList<>();
        employeeDtos.stream().forEach(i -> result.add(employeeMapper.reverse(employeesService.findById(i.getId()))));

        return result;
    }*/


}
