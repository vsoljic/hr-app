package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.employee.service.EmployeesService;
import hr.tvz.hrapp.domain.estimation.EstimationDTO;
import hr.tvz.hrapp.domain.estimation.service.EstimationService;
import hr.tvz.hrapp.domain.estimation_evaluator.service.EstimationEvaluatorService;
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
@RequestMapping("/api/admin/estimations-overview")
public class EstimationsOverviewController {

    private final EstimationService estimationService;

    private final EmployeesService employeesService;

    private final EstimationEvaluatorService estimationEvaluatorService;

    public EstimationsOverviewController(EstimationService estimationService, EmployeesService employeesService, EstimationEvaluatorService estimationEvaluatorService) {
        this.estimationService = estimationService;
        this.employeesService = employeesService;
        this.estimationEvaluatorService = estimationEvaluatorService;
    }

    @GetMapping
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<EstimationDTO>> getAllEstimations() {

        List<EstimationDTO> estimationDTOS = estimationService.findAllEstimations();

        return new ResponseEntity<>(estimationDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}/evaluators")
    public ResponseEntity<List<EmployeeDTO>> getAllEvaluatorsForEstimation(@PathVariable("id") Long estimationId) {

        List<EmployeeDTO> employeeEvaluatorsDTOS = employeesService.findAllByEstimation(estimationId);
        return new ResponseEntity<>(employeeEvaluatorsDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}/evaluators/{evaluatorId}/evaluatees")
    public ResponseEntity<List<EmployeeDTO>> getAllEvaluateesForEvaluatorAndEstimation(@PathVariable("id") Long estimationId,
                                                                                       @PathVariable("evaluatorId") Long evaluatorId) {

        List<EmployeeDTO> employeeEvaluateesDTO = employeesService.findAllEvaluateesByEvaluatorAndEstimation(estimationId, evaluatorId);
        return new ResponseEntity<>(employeeEvaluateesDTO, HttpStatus.OK);
    }

    @PostMapping("/{id}/evaluators/{evaluatorId}/evaluatees")
    public ResponseEntity<EmployeeDTO> deleteSelectedEvaluateeForEvaluatorAndEstimation(@PathVariable("id") Long estimationId,
                                                                                        @PathVariable("evaluatorId") Long evaluatorId,
                                                                                        @RequestBody EmployeeDTO employeeDTO) {


        employeesService.deleteSelectedEvaluatee(estimationId, evaluatorId, employeeDTO.getId());
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

}
