package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.employee.service.EmployeesService;
import hr.tvz.hrapp.domain.relationship_est_employees.RelationshipEstEmployeesDTO;
import hr.tvz.hrapp.domain.relationship_est_employees.service.RelationshipEstEmployeesService;
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
@RequestMapping("/api/admin/relationships")
public class RelationshipsController {

    private final EmployeesService employeesService;

    private final RelationshipEstEmployeesService relationshipEstEmployeesService;


    public RelationshipsController(EmployeesService employeesService, RelationshipEstEmployeesService relationshipEstEmployeesService) {
        this.employeesService = employeesService;
        this.relationshipEstEmployeesService = relationshipEstEmployeesService;
    }

    @GetMapping("/employees")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<EmployeeDTO>> allEmployees() {

        List<EmployeeDTO> employeeDTOS = employeesService.getAllEmployees();

        return new ResponseEntity<>(employeeDTOS, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<List<EmployeeDTO>> allEmployeesExpectSelectedOne(@PathVariable("id") Long selectedEmployeeId) {

        List<EmployeeDTO> employeeDTOS = employeesService.getAllEmployeesExceptSelectedOne(selectedEmployeeId);

        return new ResponseEntity<>(employeeDTOS, HttpStatus.OK);
    }


    @GetMapping("/employees/estimation/{estimationId}")
    public ResponseEntity<List<EmployeeDTO>> notConnectedEmployeesForEstimation(@PathVariable("estimationId") Long estimationId) {

        List<EmployeeDTO> employeeDTOS = employeesService.findNotConnectedEmployeesForEstimation(estimationId);
        return new ResponseEntity<>(employeeDTOS, HttpStatus.OK);
    }


    @GetMapping("/employees/{id}/notConnectedEmployees/{estimationId}")
    public ResponseEntity<List<EmployeeDTO>> notConnectedEmployeesForEvaluator(@PathVariable("id") Long selectedEvaluatorId,
                                                                               @PathVariable("estimationId") Long estimationId) {

        List<EmployeeDTO> employeeDTOS = employeesService.findNotConnectedEmployeesForEvaluator(selectedEvaluatorId, estimationId);
        return new ResponseEntity<>(employeeDTOS, HttpStatus.OK);
    }

    @PostMapping("/employees")
    public void saveRelationship(@RequestBody RelationshipEstEmployeesDTO relationshipEstEmployeesDTO) {

        relationshipEstEmployeesService.save(relationshipEstEmployeesDTO);
    }

    @PostMapping("/employeesList")
    public void saveRelationshipList(@RequestBody List<RelationshipEstEmployeesDTO> relationshipEstEmployeesDTO) {

        relationshipEstEmployeesService.save(relationshipEstEmployeesDTO);
    }
}
