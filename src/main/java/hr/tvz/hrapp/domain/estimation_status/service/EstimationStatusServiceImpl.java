package hr.tvz.hrapp.domain.estimation_status.service;

import hr.tvz.hrapp.domain.BaseService;
import hr.tvz.hrapp.domain.estimation_status.EstimationStatusDTO;
import hr.tvz.hrapp.domain.estimation_status.repository.EstimationStatusRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class EstimationStatusServiceImpl extends BaseService implements EstimationStatusService {

    private final EstimationStatusRepository estimationStatusRepository;

    public EstimationStatusServiceImpl(DozerBeanMapper dozerBeanMapper, EstimationStatusRepository estimationStatusRepository) {
        super(dozerBeanMapper);
        this.estimationStatusRepository = estimationStatusRepository;
    }

    @Override
    public List<EstimationStatusDTO> findAllEstimationStatuses() {
        return mapEntitiesToDTO(estimationStatusRepository.findAll(), EstimationStatusDTO.class);
    }
}
