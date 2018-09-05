package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.estimation.EstimationDTO;
import hr.tvz.hrapp.domain.estimation.service.EstimationService;
import hr.tvz.hrapp.security.AuthoritiesConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author vedrana.soljic
 */
@RestController
@RequestMapping("/api/admin/estimation")
public class EstimationController {

    private final EstimationService estimationService;

    public EstimationController(EstimationService estimationService) {
        this.estimationService = estimationService;
    }

    @PostMapping
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<EstimationDTO> createNewEstimation(@RequestBody EstimationDTO estimationDTO) {

        EstimationDTO dto = estimationService.createNewEstimation(estimationDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/save")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<EstimationDTO> saveSelectedEstimation(@RequestBody EstimationDTO estimationDTO) {

        EstimationDTO dto = estimationService.saveSelectedEstimation(estimationDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
