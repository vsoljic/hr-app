package hr.tvz.hrapp.domain.estimation.service;

import hr.tvz.hrapp.domain.BaseService;
import hr.tvz.hrapp.domain.estimation.Estimation;
import hr.tvz.hrapp.domain.estimation.EstimationDTO;
import hr.tvz.hrapp.domain.estimation.repository.EstimationRepository;
import hr.tvz.hrapp.domain.estimation_status.repository.EstimationStatusRepository;
import hr.tvz.hrapp.domain.model.repository.ModelRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class EstimationServiceImpl extends BaseService implements EstimationService {

    private final EstimationRepository estimationRepository;

    private final EstimationStatusRepository estimationStatusRepository;

    private final ModelRepository modelRepository;

    public EstimationServiceImpl(DozerBeanMapper dozerBeanMapper, EstimationRepository estimationRepository,
                                 EstimationStatusRepository estimationStatusRepository, ModelRepository modelRepository) {
        super(dozerBeanMapper);
        this.estimationRepository = estimationRepository;
        this.estimationStatusRepository = estimationStatusRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<EstimationDTO> findAllEstimations() {
        return mapEntitiesToDTO(estimationRepository.findAllByActivityNot(0), EstimationDTO.class);
    }

    @Override
    public EstimationDTO createNewEstimation(EstimationDTO estimationDTO) {

        Estimation estimation = mapDTOToEntity(estimationDTO, Estimation.class);
        estimation.setActivity(1);

        Estimation newEstimation = estimationRepository.save(estimation);

        return mapEntityToDTO(newEstimation, EstimationDTO.class);
    }

    @Override
    public EstimationDTO editSelectedEstimation(EstimationDTO estimationDTO) {
        Estimation estimation = mapDTOToEntity(estimationDTO, Estimation.class);
        estimation.setActivity(0);
        estimationRepository.save(estimation);

        Estimation newEstimation = new Estimation();
        newEstimation.setModel(estimation.getModel());
        newEstimation.setActivity(1);
        newEstimation.setStatus(estimation.getStatus());
        newEstimation.setName(estimation.getName());
        newEstimation.setPeriodFrom(estimation.getPeriodFrom());
        newEstimation.setPeriodTo(estimation.getPeriodTo());
        newEstimation.setEmployeesEvaluatees(estimation.getEmployeesEvaluatees());
        newEstimation.setEmployeesEvaluators(estimation.getEmployeesEvaluators());


        newEstimation = estimationRepository.save(newEstimation);

        return mapEntityToDTO(newEstimation, EstimationDTO.class);
    }
}
