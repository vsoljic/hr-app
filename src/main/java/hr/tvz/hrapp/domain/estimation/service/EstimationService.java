package hr.tvz.hrapp.domain.estimation.service;

import hr.tvz.hrapp.domain.estimation.EstimationDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface EstimationService {

    List<EstimationDTO> findAllEstimations();

    EstimationDTO createNewEstimation(EstimationDTO estimationDTO);

    EstimationDTO editSelectedEstimation(EstimationDTO estimationDTO);
}
