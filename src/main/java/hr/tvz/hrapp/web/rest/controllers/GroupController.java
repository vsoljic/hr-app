package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.group.GroupDTO;
import hr.tvz.hrapp.domain.group.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/estimation/{estimationId}/employee/{employeeId}")
    public ResponseEntity<List<GroupDTO>> goalsByEmployeeAndGroupAndEst(@PathVariable(name = "estimationId") Long estimationId,
                                                                        @PathVariable(name = "employeeId") Long employeeId) {

        List<GroupDTO> groupDTOS = groupService.findAllByEmployeeAndEstimationGroupByGroups(estimationId, employeeId);

        return new ResponseEntity<>(groupDTOS, HttpStatus.OK);
    }
}
