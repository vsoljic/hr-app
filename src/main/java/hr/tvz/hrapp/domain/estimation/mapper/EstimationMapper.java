package hr.tvz.hrapp.domain.estimation.mapper;

import hr.tvz.hrapp.domain.estimation.Estimation;
import hr.tvz.hrapp.domain.estimation.EstimationDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface EstimationMapper {

    EstimationDTO mapToDto(Estimation estimation);

    Estimation reverse(EstimationDTO estimationDTO);

    List<EstimationDTO> mapListToDtoList(List<Estimation> estimations);

    List<Estimation> mapDtoListToList(List<EstimationDTO> estimationDTOS);
}
