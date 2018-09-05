package hr.tvz.hrapp.domain.estimation_status.mapper;

import hr.tvz.hrapp.domain.estimation_status.EstimationStatus;
import hr.tvz.hrapp.domain.estimation_status.EstimationStatusDTO;

/**
 * @author vedrana.soljic
 */
public interface EstimationStatusMapper {

    EstimationStatusDTO mapToDto(EstimationStatus status);

    EstimationStatus reverse(EstimationStatusDTO dto);
}
