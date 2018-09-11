package hr.tvz.hrapp.domain.goal.service;

import hr.tvz.hrapp.domain.goal.GoalDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface GoalService {

    List<GoalDTO> findAllGoals();

    List<GoalDTO> findAllByEmployeeAndGroupAndEst(Long estimationId, Long groupId, Long employeeId);

    GoalDTO saveGoal(GoalDTO goalDTO);
}
