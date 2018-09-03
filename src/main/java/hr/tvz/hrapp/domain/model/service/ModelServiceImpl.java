package hr.tvz.hrapp.domain.model.service;

import hr.tvz.hrapp.domain.BaseService;
import hr.tvz.hrapp.domain.model.Model;
import hr.tvz.hrapp.domain.model.ModelDTO;
import hr.tvz.hrapp.domain.model.repository.ModelRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class ModelServiceImpl extends BaseService implements ModelService {

    private final ModelRepository modelRepository;

    public ModelServiceImpl(DozerBeanMapper dozerBeanMapper, ModelRepository modelRepository) {
        super(dozerBeanMapper);
        this.modelRepository = modelRepository;
    }

    @Override
    public List<ModelDTO> findAllModels() {
        List<Model> models = modelRepository.findAll();

        return mapEntitiesToDTO(models, ModelDTO.class);
    }

  /*  @Override
    public List<ModelDTO> findByEstimationId(Long estimationId) {
        List<Model> models = modelRepository.

        return mapEntitiesToDTO(models, ModelDTO.class);
    }*/
}
