package hr.tvz.hrapp.domain.estimation_model.mapper;

import hr.tvz.hrapp.domain.estimation_model.EstimationModel;
import hr.tvz.hrapp.domain.estimation_model.EstimationModelDTO;

/**
 * @author vedrana.soljic
 */
public interface EstimationModelMapper {
    EstimationModelDTO mapToDto(EstimationModel estimationModel);

    EstimationModel reverse(EstimationModelDTO dto);
}
