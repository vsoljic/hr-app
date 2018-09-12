package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.estimation.EstimationDTO;
import hr.tvz.hrapp.domain.estimation.service.EstimationService;
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
@RequestMapping("/api/estimations-evaluator")
public class EstimationsEvaluatorController {

    private final EstimationService estimationService;

    public EstimationsEvaluatorController(EstimationService estimationService) {
        this.estimationService = estimationService;
    }

    @GetMapping("/{evaluatorId}")
    public ResponseEntity<List<EstimationDTO>> getAllEstimationsForEvaluator(@PathVariable(name = "evaluatorId") Long evaluatorId) {

        List<EstimationDTO> estimations = estimationService.findAllByLoggedInUser(evaluatorId);
        return new ResponseEntity<>(estimations, HttpStatus.OK);
    }
}
