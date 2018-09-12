package hr.tvz.hrapp.domain.group.service;

import hr.tvz.hrapp.domain.estimation.EstimationDTO;
import hr.tvz.hrapp.domain.estimation.service.EstimationService;
import hr.tvz.hrapp.domain.goal.GoalDTO;
import hr.tvz.hrapp.domain.goal.service.GoalService;
import hr.tvz.hrapp.domain.group.GroupDTO;
import hr.tvz.hrapp.domain.group.mapper.GroupMapper;
import hr.tvz.hrapp.domain.group.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    private final GoalService goalService;

    private final EstimationService estimationService;

    private final GroupMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository, GoalService goalService,
                            EstimationService estimationService, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.goalService = goalService;
        this.estimationService = estimationService;
        this.groupMapper = groupMapper;
    }

    @Override
    public List<GroupDTO> findAllGroups() {
        return groupMapper.mapListToDtoList(groupRepository.findAll());
    }


    @Override
    public List<GroupDTO> findAllByEmployeeAndEstimationGroupByGroups(Long estimationId, Long employeeId) {
        EstimationDTO estimation = estimationService.findById(estimationId);
        List<GroupDTO> groups = estimation.getModel().getGroups();



        groups.forEach(dto -> {
            List<GoalDTO> goals = goalService.findAllByEmployeeAndGroupAndEst(estimationId, dto.getId(), employeeId);
            Long totalForGroup = 0L;

            for (GoalDTO goalDTO : goals) {
                totalForGroup += goalDTO.getPonderPercentage();
            }

            dto.setTotalPonderForGroup(totalForGroup);
            dto.setGoals(goals);
        });


        return groups;
    }
}
