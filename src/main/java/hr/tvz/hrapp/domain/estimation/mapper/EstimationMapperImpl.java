package hr.tvz.hrapp.domain.estimation.mapper;

import hr.tvz.hrapp.domain.employee.mapper.EmployeeMapper;
import hr.tvz.hrapp.domain.estimation.Estimation;
import hr.tvz.hrapp.domain.estimation.EstimationDTO;
import hr.tvz.hrapp.domain.estimation_status.mapper.EstimationStatusMapper;
import hr.tvz.hrapp.domain.estimation_model.mapper.EstimationModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vedrana.soljic
 */
@Component
public class EstimationMapperImpl implements EstimationMapper {

    private final EmployeeMapper employeeMapper;
    private final EstimationModelMapper estimationModelMapper;
    private final EstimationStatusMapper estimationStatusMapper;

    public EstimationMapperImpl(EmployeeMapper employeeMapper, EstimationModelMapper estimationModelMapper, EstimationStatusMapper estimationStatusMapper) {
        this.employeeMapper = employeeMapper;
        this.estimationModelMapper = estimationModelMapper;
        this.estimationStatusMapper = estimationStatusMapper;
    }

    @Override
    public EstimationDTO mapToDto(Estimation estimation) {

        EstimationDTO estimationDTO = new EstimationDTO();
        estimationDTO.setId(estimation.getId());
        estimationDTO.setModel(estimationModelMapper.mapToDto(estimation.getEstimationModel()));
        estimationDTO.setName(estimation.getName());
        estimationDTO.setPeriodFrom(estimation.getPeriodFrom());
        estimationDTO.setPeriodTo(estimation.getPeriodTo());
        estimationDTO.setActivity(estimation.getActivity());
        estimationDTO.setStatus(estimationStatusMapper.mapToDto(estimation.getStatus()));

        estimationDTO.setEmployeesEvaluators(employeeMapper.mapListToDtoList(estimation.getEmployeesEvaluators()));
        estimationDTO.setEmployeesEvaluatees(employeeMapper.mapListToDtoList(estimation.getEmployeesEvaluatees()));

        return estimationDTO;
    }

    @Override
    public Estimation reverse(EstimationDTO estimationDTO) {

        Estimation estimation = new Estimation();
        estimation.setId(estimationDTO.getId());
        estimation.setEstimationModel(estimationModelMapper.reverse(estimationDTO.getModel()));
        estimation.setName(estimationDTO.getName());
        estimation.setPeriodFrom(estimationDTO.getPeriodFrom());
        estimation.setPeriodTo(estimationDTO.getPeriodTo());
        estimation.setActivity(estimationDTO.getActivity());
        estimation.setStatus(estimationStatusMapper.reverse(estimationDTO.getStatus()));

        estimation.setEmployeesEvaluatees(employeeMapper.mapDtoListToList(estimationDTO.getEmployeesEvaluatees()));
        estimation.setEmployeesEvaluators(employeeMapper.mapDtoListToList(estimationDTO.getEmployeesEvaluators()));

        return estimation;
    }

    @Override
    public List<EstimationDTO> mapListToDtoList(List<Estimation> estimations) {
        List<EstimationDTO> result = new ArrayList<>();
        estimations.stream().forEach(i -> result.add(mapToDto(i)));

        return result;
    }

    @Override
    public List<Estimation> mapDtoListToList(List<EstimationDTO> estimationDTOS) {
        List<Estimation> result = new ArrayList<>();
        estimationDTOS.stream().forEach(i -> result.add(reverse(i)));

        return result;
    }
}
