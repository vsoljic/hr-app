package hr.tvz.hrapp.domain.estimation_model.service;

import hr.tvz.hrapp.domain.estimation_model.EstimationModelDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface EstimationModelService {

    List<EstimationModelDTO> findAllModels();
}
