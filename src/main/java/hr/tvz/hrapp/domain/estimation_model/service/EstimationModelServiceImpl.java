package hr.tvz.hrapp.domain.estimation_model.service;

import hr.tvz.hrapp.domain.BaseService;
import hr.tvz.hrapp.domain.estimation_model.EstimationModel;
import hr.tvz.hrapp.domain.estimation_model.EstimationModelDTO;
import hr.tvz.hrapp.domain.estimation_model.repository.EstimationModelRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class EstimationModelServiceImpl extends BaseService implements EstimationModelService {

    private final EstimationModelRepository estimationModelRepository;

    public EstimationModelServiceImpl(DozerBeanMapper dozerBeanMapper, EstimationModelRepository estimationModelRepository) {
        super(dozerBeanMapper);
        this.estimationModelRepository = estimationModelRepository;
    }

    @Override
    public List<EstimationModelDTO> findAllModels() {
        List<EstimationModel> estimationModels = estimationModelRepository.findAll();

        return mapEntitiesToDTO(estimationModels, EstimationModelDTO.class);
    }

  /*  @Override
    public List<EstimationModelDTO> findByEstimationId(Long estimationId) {
        List<EstimationModel> models = modelRepository.

        return mapEntitiesToDTO(models, EstimationModelDTO.class);
    }*/
}
