package hr.tvz.hrapp.domain.goal.mapper;

import hr.tvz.hrapp.domain.employee.mapper.EmployeeMapper;
import hr.tvz.hrapp.domain.estimation.mapper.EstimationMapper;
import hr.tvz.hrapp.domain.goal.Goal;
import hr.tvz.hrapp.domain.goal.GoalDTO;
import hr.tvz.hrapp.domain.group.mapper.GroupMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author vedrana.soljic
 */
@Component
public class GoalMapperImpl implements GoalMapper {

    private final EstimationMapper estimationMapper;

    private final GroupMapper groupMapper;

    private final EmployeeMapper employeeMapper;

    public GoalMapperImpl(EstimationMapper estimationMapper, GroupMapper groupMapper, EmployeeMapper employeeMapper) {
        this.estimationMapper = estimationMapper;
        this.groupMapper = groupMapper;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public GoalDTO mapToDto(Goal goal) {
        GoalDTO dto = new GoalDTO();

        dto.setId(goal.getId());
        dto.setName(goal.getName());
        dto.setAchievementPercentage(goal.getAchievementPercentage());
        dto.setPonderPercentage(goal.getPonderPercentage());
        dto.setTargetValue(goal.getTargetValue());
        dto.setEstimation(estimationMapper.mapToDto(goal.getEstimation()));
        dto.setGroup(groupMapper.mapToDto(goal.getGroup()));
        dto.setEmployee(employeeMapper.mapToDto(goal.getEmployee()));

        return dto;
    }

    @Override
    public Goal reverse(GoalDTO dto) {
        Goal goal = new Goal();

        goal.setId(dto.getId());
        goal.setName(dto.getName());
        goal.setAchievementPercentage(dto.getAchievementPercentage());
        goal.setPonderPercentage(dto.getPonderPercentage());
        goal.setTargetValue(dto.getTargetValue());
        goal.setEstimation(estimationMapper.reverse(dto.getEstimation()));
        goal.setGroup(groupMapper.reverse(dto.getGroup()));
        goal.setEmployee(employeeMapper.reverse(dto.getEmployee()));

        return goal;
    }

    @Override
    public List<GoalDTO> mapListToDtoList(List<Goal> goals) {

        List<GoalDTO> goalDTOS = new ArrayList<>();
        goals.stream().forEach(i -> goalDTOS.add(mapToDto(i)));

        return goalDTOS;
    }

    @Override
    public List<Goal> mapDtoListToList(List<GoalDTO> goalDTOList) {

        List<Goal> goals = new ArrayList<>();
        goalDTOList.stream().forEach(i -> goals.add(reverse(i)));

        return goals;
    }


}
