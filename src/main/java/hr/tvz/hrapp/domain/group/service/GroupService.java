package hr.tvz.hrapp.domain.group.service;

import hr.tvz.hrapp.domain.group.GroupDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface GroupService {

    List<GroupDTO> findAllGroups();

    List<GroupDTO> findAllByEmployeeAndEstimationGroupByGroups(Long estimationId, Long employeeId);
}
