package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.estimation.EstimationDTO;
import hr.tvz.hrapp.domain.estimation.service.EstimationService;
import hr.tvz.hrapp.domain.model.ModelDTO;
import hr.tvz.hrapp.security.AuthoritiesConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@RestController
@RequestMapping("/api/admin/estimations-overview")
public class EstimationsOverviewController {

    private final EstimationService estimationService;

    public EstimationsOverviewController(EstimationService estimationService) {
        this.estimationService = estimationService;
    }

    @GetMapping
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<EstimationDTO>> getAllEstimations() {

        List<EstimationDTO> estimationDTOS = estimationService.findAllEstimations();

        return new ResponseEntity<>(estimationDTOS, HttpStatus.OK);
    }

}
