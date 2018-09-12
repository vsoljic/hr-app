package hr.tvz.hrapp.web.rest.controllers;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.employee.service.EmployeesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vedrana.soljic
 */
@RestController
@RequestMapping("api/employee-user")
public class EmployeeUserController {

    private final EmployeesService employeesService;

    public EmployeeUserController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public ResponseEntity<EmployeeDTO> getEmployeeForUser() {

        EmployeeDTO employeeDTO = employeesService.getEmployeeForLoggedInUser();

        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }
}
