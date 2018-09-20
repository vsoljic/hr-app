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

@RestController
@RequestMapping("/api/estimations-evaluatee")
public class EstimationsEvaluateeController {

    private final EstimationService estimationService;

    public EstimationsEvaluateeController(EstimationService estimationService) {
        this.estimationService = estimationService;
    }

    @GetMapping("/{evaluateeId}")
    public ResponseEntity<List<EstimationDTO>> getAllEstimationsForEvaluatee(@PathVariable(name = "evaluateeId") Long evaluateeId) {

        List<EstimationDTO> estimations = estimationService.findAllByLoggedInUserEvaluatee(evaluateeId);
        return new ResponseEntity<>(estimations, HttpStatus.OK);
    }
}
