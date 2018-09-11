package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.goal.GoalDTO;
import hr.tvz.hrapp.domain.goal.service.GoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/estimation/{estimationId}/group/{groupId}/employee/{employeeId}")
    public ResponseEntity<List<GoalDTO>> goalsByEmployeeAndGroupAndEst(@PathVariable(name = "estimationId") Long estimationId,
                                                                       @PathVariable(name = "groupId") Long groupId,
                                                                       @PathVariable(name = "employeeId") Long employeeId) {

        List<GoalDTO> goalDTOS = goalService.findAllByEmployeeAndGroupAndEst(estimationId, groupId, employeeId);

        return new ResponseEntity<>(goalDTOS, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<GoalDTO> createNewGoal(@RequestBody GoalDTO goalDTO) {
        GoalDTO dto = goalService.saveGoal(goalDTO);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
