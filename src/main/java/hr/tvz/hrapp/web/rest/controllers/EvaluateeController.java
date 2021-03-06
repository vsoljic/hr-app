package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.estimation.EstimationDTO;
import hr.tvz.hrapp.domain.estimation.service.EstimationService;
import hr.tvz.hrapp.domain.group.Group;
import hr.tvz.hrapp.domain.group.GroupDTO;
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
@RequestMapping("/api/evaluatee")
public class EvaluateeController {

    private final EstimationService estimationService;

    public EvaluateeController(EstimationService estimationService) {
        this.estimationService = estimationService;
    }

    @GetMapping("/estimation/{id}/groups")
    public ResponseEntity<List<GroupDTO>> getGroupsByEstimation(@PathVariable("id") Long estimationId) {

        EstimationDTO estimations = estimationService.findById(estimationId);
        List<GroupDTO> groups = estimations.getModel().getGroups();

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }
}
