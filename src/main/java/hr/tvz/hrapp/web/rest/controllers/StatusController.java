package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.estimation_status.EstimationStatusDTO;
import hr.tvz.hrapp.domain.estimation_status.service.EstimationStatusService;
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
@RequestMapping("/api/admin/estimation/statuses")
public class StatusController {

    private final EstimationStatusService estimationStatusService;

    public StatusController(EstimationStatusService estimationStatusService) {
        this.estimationStatusService = estimationStatusService;
    }

    @GetMapping
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<EstimationStatusDTO>> getAllStatuses() {

        List<EstimationStatusDTO> estimationStatusDTOS = estimationStatusService.findAllEstimationStatuses();
        return new ResponseEntity<>(estimationStatusDTOS, HttpStatus.OK);
    }
}
