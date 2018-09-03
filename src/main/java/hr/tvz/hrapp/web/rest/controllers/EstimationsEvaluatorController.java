package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.estimation.Estimation;
import hr.tvz.hrapp.domain.estimation.EstimationDTO;
import hr.tvz.hrapp.domain.estimation.service.EstimationService;
import hr.tvz.hrapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@RestController
@RequestMapping("/api/estimations-evaluator")
public class EstimationsEvaluatorController {

    private final UserService userService;

    private final EstimationService estimationService;

    public EstimationsEvaluatorController(UserService userService, EstimationService estimationService) {
        this.userService = userService;
        this.estimationService = estimationService;
    }

    @GetMapping
    public ResponseEntity<List<EstimationDTO>> getAllEstimationsForEvaluator() {

        List<EstimationDTO> estimations = estimationService.findAllByLoggedInUser();
        return new ResponseEntity<>(estimations, HttpStatus.OK);
    }
}
