package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.goal.GoalDTO;
import hr.tvz.hrapp.domain.goal.service.GoalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(GoalController.class);

    private final GoalService goalService;


    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }


    @GetMapping("/estimation/{estimationId}/employee/{employeeId}")
    public ResponseEntity<List<GoalDTO>> goalsByEmployeeAndEstimation(@PathVariable(name = "estimationId") Long estimationId,
                                                                      @PathVariable(name = "employeeId") Long employeeId) {

        //  List<GoalDTO> goalDTOS = goalService.findAllByEmployeeAndEstimationGroupByGroups(estimationId, employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<GoalDTO> createNewGoal(@RequestBody GoalDTO goalDTO) {

        try {
            GoalDTO dto = goalService.saveGoal(goalDTO);
            return new ResponseEntity<>(dto, HttpStatus.OK);

        } catch (IllegalArgumentException ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }
}
