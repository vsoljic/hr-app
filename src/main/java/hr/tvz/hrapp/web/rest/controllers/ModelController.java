package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.estimation_model.EstimationModelDTO;
import hr.tvz.hrapp.domain.estimation_model.service.EstimationModelService;
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
@RequestMapping("/api/admin/estimation/model")
public class ModelController {

    private final EstimationModelService estimationModelService;

    public ModelController(EstimationModelService estimationModelService) {
        this.estimationModelService = estimationModelService;
    }

    @GetMapping
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<EstimationModelDTO>> getAllModels() {

        List<EstimationModelDTO> estimationModelDTOS = estimationModelService.findAllModels();
        return new ResponseEntity<>(estimationModelDTOS, HttpStatus.OK);
    }
/*
    @GetMapping("/{estimationId}")
    public ResponseEntity<List<EstimationModelDTO>> getModelByEstimation(@PathVariable("estimationId") Long id) {

        List<EstimationModelDTO> modelDTOS = estimationModelService.
        return new ResponseEntity<>(modelDTOS, HttpStatus.OK);
    }*/
}
