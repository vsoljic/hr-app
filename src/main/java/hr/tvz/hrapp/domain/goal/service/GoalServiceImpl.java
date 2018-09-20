package hr.tvz.hrapp.domain.goal.service;

import hr.tvz.hrapp.domain.goal.Goal;
import hr.tvz.hrapp.domain.goal.GoalDTO;
import hr.tvz.hrapp.domain.goal.mapper.GoalMapper;
import hr.tvz.hrapp.domain.goal.repository.GoalRepository;
import hr.tvz.hrapp.domain.group.service.GroupService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    private final GoalMapper goalMapper;

    private final GroupService groupService;

    public GoalServiceImpl(GoalRepository goalRepository, GoalMapper goalMapper, @Lazy GroupService groupService) {
        this.goalRepository = goalRepository;
        this.goalMapper = goalMapper;
        this.groupService = groupService;
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

        Goal goal = goalMapper.reverse(goalDTO);
        Goal savedGoal = goalRepository.save(goal);

        return goalMapper.mapToDto(savedGoal);
    }

}
