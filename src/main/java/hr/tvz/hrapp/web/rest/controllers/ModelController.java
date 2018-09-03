package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.model.ModelDTO;
import hr.tvz.hrapp.domain.model.service.ModelService;
import hr.tvz.hrapp.security.AuthoritiesConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@RestController
@RequestMapping("/api/admin/estimation/model")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<ModelDTO>> getAllModels() {

        List<ModelDTO> modelDTOS = modelService.findAllModels();
        return new ResponseEntity<>(modelDTOS, HttpStatus.OK);
    }
/*
    @GetMapping("/{estimationId}")
    public ResponseEntity<List<ModelDTO>> getModelByEstimation(@PathVariable("estimationId") Long id) {

        List<ModelDTO> modelDTOS = modelService.
        return new ResponseEntity<>(modelDTOS, HttpStatus.OK);
    }*/
}
