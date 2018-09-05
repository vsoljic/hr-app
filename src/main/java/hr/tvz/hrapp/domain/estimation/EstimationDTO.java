package hr.tvz.hrapp.domain.estimation;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.estimation_model.EstimationModelDTO;
import hr.tvz.hrapp.domain.estimation_status.EstimationStatusDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author vedrana.soljic
 */
public class EstimationDTO implements Serializable {

    private Long id;

    private EstimationStatusDTO status;

    private EstimationModelDTO model;

    private String name;

    private LocalDate periodFrom;

    private LocalDate periodTo;

    private Integer activity;

    private List<EmployeeDTO> employeesEvaluators;

    private List<EmployeeDTO> employeesEvaluatees;

    public EstimationDTO() {
    }

    public EstimationDTO(Long id, EstimationStatusDTO status, EstimationModelDTO model, String name, LocalDate periodFrom,
                         LocalDate periodTo, Integer activity, List<EmployeeDTO> employeesEvaluators, List<EmployeeDTO> employeesEvaluatees) {
        this.id = id;
        this.status = status;
        this.model = model;
        this.name = name;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.activity = activity;
        this.employeesEvaluators = employeesEvaluators;
        this.employeesEvaluatees = employeesEvaluatees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstimationStatusDTO getStatus() {
        return status;
    }

    public void setStatus(EstimationStatusDTO status) {
        this.status = status;
    }

    public EstimationModelDTO getModel() {
        return model;
    }

    public void setModel(EstimationModelDTO model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(LocalDate periodFrom) {
        this.periodFrom = periodFrom;
    }

    public LocalDate getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(LocalDate periodTo) {
        this.periodTo = periodTo;
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public List<EmployeeDTO> getEmployeesEvaluators() {
        return employeesEvaluators;
    }

    public void setEmployeesEvaluators(List<EmployeeDTO> employeesEvaluators) {
        this.employeesEvaluators = employeesEvaluators;
    }

    public List<EmployeeDTO> getEmployeesEvaluatees() {
        return employeesEvaluatees;
    }

    public void setEmployeesEvaluatees(List<EmployeeDTO> employeesEvaluatees) {
        this.employeesEvaluatees = employeesEvaluatees;
    }
}
