package hr.tvz.hrapp.domain.estimation_status.service;

import hr.tvz.hrapp.domain.estimation_status.EstimationStatusDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface EstimationStatusService {

    List<EstimationStatusDTO> findAllEstimationStatuses();
}
