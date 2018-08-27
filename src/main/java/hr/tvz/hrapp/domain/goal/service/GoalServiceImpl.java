package hr.tvz.hrapp.domain.goal.service;

import hr.tvz.hrapp.domain.BaseService;
import hr.tvz.hrapp.domain.goal.GoalDTO;
import hr.tvz.hrapp.domain.goal.repository.GoalRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class GoalServiceImpl extends BaseService implements GoalService {

    private final GoalRepository goalRepository;

    public GoalServiceImpl(DozerBeanMapper dozerBeanMapper, GoalRepository goalRepository) {
        super(dozerBeanMapper);
        this.goalRepository = goalRepository;
    }

    @Override
    public List<GoalDTO> findAllGoals() {
        return mapEntitiesToDTO(goalRepository.findAll(), GoalDTO.class);
    }
}
