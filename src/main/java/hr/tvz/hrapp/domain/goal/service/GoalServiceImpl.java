package hr.tvz.hrapp.domain.goal.service;

import hr.tvz.hrapp.domain.goal.Goal;
import hr.tvz.hrapp.domain.goal.GoalDTO;
import hr.tvz.hrapp.domain.goal.mapper.GoalMapper;
import hr.tvz.hrapp.domain.goal.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    private final GoalMapper goalMapper;

    public GoalServiceImpl(GoalRepository goalRepository, GoalMapper goalMapper) {
        this.goalRepository = goalRepository;
        this.goalMapper = goalMapper;
    }

    @Override
    public List<GoalDTO> findAllGoals() {
        return goalMapper.mapListToDtoList(goalRepository.findAll());
    }

    @Override
    public List<GoalDTO> findAllByEmployeeAndGroupAndEst(Long estimationId, Long groupId, Long employeeId) {
        return goalMapper.mapListToDtoList(goalRepository.findAllByEstimationIdAndGroupIdAndEmployeeId(estimationId, groupId, employeeId));
    }

    @Override
    public GoalDTO saveGoal(GoalDTO goalDTO) {

        Long sum = goalDTO.getPonderPercentage() + goalDTO.getGroup().getTotalPonderForGroup();
        if (sum > 100) {
            throw new IllegalArgumentException();
        }

        Goal goal = goalMapper.reverse(goalDTO);
        Goal savedGoal = goalRepository.save(goal);

        return goalMapper.mapToDto(savedGoal);
    }
}
