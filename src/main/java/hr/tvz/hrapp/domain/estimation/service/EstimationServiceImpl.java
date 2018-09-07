package hr.tvz.hrapp.domain.estimation.service;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.employee.service.EmployeesService;
import hr.tvz.hrapp.domain.estimation.Estimation;
import hr.tvz.hrapp.domain.estimation.EstimationDTO;
import hr.tvz.hrapp.domain.estimation.mapper.EstimationMapper;
import hr.tvz.hrapp.domain.estimation.repository.EstimationRepository;
import hr.tvz.hrapp.domain.estimation_status.mapper.EstimationStatusMapper;
import hr.tvz.hrapp.domain.estimation_model.mapper.EstimationModelMapper;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployeesDTO;
import hr.tvz.hrapp.domain.relationship_est_employees.service.RelationshipEstEmployeesService;
import hr.tvz.hrapp.service.UserService;
import hr.tvz.hrapp.service.dto.UserDTO;
import hr.tvz.hrapp.web.rest.errors.InternalServerErrorException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author vedrana.soljic
 */
@Service
public class EstimationServiceImpl implements EstimationService {

    private final EstimationRepository estimationRepository;
    private final EmployeesService employeesService;
    private final RelationshipEstEmployeesService relationshipEstEmployeesService;
    private final UserService userService;
    private final EstimationMapper estimationMapper;
    private final EstimationModelMapper estimationModelMapper;
    private final EstimationStatusMapper estimationStatusMapper;

    public EstimationServiceImpl(EstimationRepository estimationRepository, EmployeesService employeesService,
                                 RelationshipEstEmployeesService relationshipEstEmployeesService, UserService userService,
                                 EstimationMapper estimationMapper, EstimationModelMapper estimationModelMapper,
                                 EstimationStatusMapper estimationStatusMapper) {
        this.estimationRepository = estimationRepository;
        this.employeesService = employeesService;
        this.relationshipEstEmployeesService = relationshipEstEmployeesService;
        this.userService = userService;
        this.estimationMapper = estimationMapper;
        this.estimationModelMapper = estimationModelMapper;
        this.estimationStatusMapper = estimationStatusMapper;
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
    public List<EstimationDTO> findAllByLoggedInUser() {
        UserDTO userDTO = userService.getUserWithAuthorities().map(UserDTO::new)
            .orElseThrow(() -> new InternalServerErrorException("User could not be found"));

        EmployeeDTO employeeDTO = employeesService.findByUserId(userDTO.getId());

        List<RelationshipEstEmployeesDTO> relationships = relationshipEstEmployeesService.findAllForEvaluator(employeeDTO.getId());

        List<EstimationDTO> estimationsDtos = new ArrayList<>();
        relationships.stream().forEach(relationshipEstEmployeesDTO -> estimationsDtos.add(estimationMapper.mapToDto(estimationRepository.findById(relationshipEstEmployeesDTO.getEstimationId()).get())));

        return estimationsDtos;
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
