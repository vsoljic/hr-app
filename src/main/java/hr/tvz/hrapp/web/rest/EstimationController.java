package hr.tvz.hrapp.web.rest;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.employee.service.EmployeesService;
import hr.tvz.hrapp.domain.model.ModelDTO;
import hr.tvz.hrapp.domain.model.service.ModelService;
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
@RequestMapping("/api/admin")
public class EstimationController {

    private final EmployeesService employeesService;

    private final ModelService modelService;

    public EstimationController(EmployeesService employeesService, ModelService modelService) {
        this.employeesService = employeesService;
        this.modelService = modelService;
    }

    @GetMapping("/estimation")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<EmployeeDTO>> allEmployees() {

        List<EmployeeDTO> employeeDTOS = employeesService.getAllEmployees();


        return new ResponseEntity<>(employeeDTOS, HttpStatus.OK);
    }

    @GetMapping("/estimation/model")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<ModelDTO>> getAllModels() {

        List<ModelDTO> modelDTOS = modelService.findAllModels();

        return new ResponseEntity<>(modelDTOS, HttpStatus.OK);
    }
}
