package hr.tvz.hrapp.domain.goal.mapper;

import hr.tvz.hrapp.domain.goal.Goal;
import hr.tvz.hrapp.domain.goal.GoalDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface GoalMapper {

    GoalDTO mapToDto(Goal goal);

    Goal reverse(GoalDTO dto);

    List<GoalDTO> mapListToDtoList(List<Goal> goals);

    List<Goal> mapDtoListToList(List<GoalDTO> goalDTOList);
}
