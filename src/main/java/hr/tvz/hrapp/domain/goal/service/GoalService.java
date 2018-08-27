package hr.tvz.hrapp.domain.goal.service;

import hr.tvz.hrapp.domain.goal.GoalDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface GoalService {

    List<GoalDTO> findAllGoals();
}
