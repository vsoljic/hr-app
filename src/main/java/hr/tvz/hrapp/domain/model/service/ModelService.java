package hr.tvz.hrapp.domain.model.service;

import hr.tvz.hrapp.domain.model.ModelDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface ModelService {

    List<ModelDTO> findAllModels();
}
