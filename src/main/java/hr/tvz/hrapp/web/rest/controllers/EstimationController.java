package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.employee.service.EmployeesService;
import hr.tvz.hrapp.domain.estimation.EstimationDTO;
import hr.tvz.hrapp.domain.estimation.service.EstimationService;
import hr.tvz.hrapp.domain.estimation_status.EstimationStatusDTO;
import hr.tvz.hrapp.domain.estimation_status.service.EstimationStatusService;
import hr.tvz.hrapp.domain.model.ModelDTO;
import hr.tvz.hrapp.domain.model.service.ModelService;
import hr.tvz.hrapp.security.AuthoritiesConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@RestController
@RequestMapping("/api/admin/estimation")
public class EstimationController {

    private final ModelService modelService;

    private final EstimationService estimationService;

    private final EstimationStatusService estimationStatusService;


    public EstimationController(ModelService modelService, EstimationService estimationService, EstimationStatusService estimationStatusService) {
        this.modelService = modelService;
        this.estimationService = estimationService;
        this.estimationStatusService = estimationStatusService;
    }


    @GetMapping("/model")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<ModelDTO>> getAllModels() {

        List<ModelDTO> modelDTOS = modelService.findAllModels();

        return new ResponseEntity<>(modelDTOS, HttpStatus.OK);
    }

    @GetMapping("/statuses")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<EstimationStatusDTO>> getAllStatuses() {

        List<EstimationStatusDTO> estimationStatusDTOS = estimationStatusService.findAllEstimationStatuses();

        return new ResponseEntity<>(estimationStatusDTOS, HttpStatus.OK);
    }

    @PostMapping
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<EstimationDTO> saveNewEstimation(@RequestBody EstimationDTO estimationDTO) {

        EstimationDTO dto = estimationService.createNewEstimation(estimationDTO);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
