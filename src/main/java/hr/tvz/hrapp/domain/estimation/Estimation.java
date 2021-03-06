package hr.tvz.hrapp.domain.estimation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.estimation_model.EstimationModel;
import hr.tvz.hrapp.domain.estimation_status.EstimationStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vedrana.soljic
 */
@Entity
@Table(name = "ESTIMATION")
public class Estimation implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID")
    private EstimationStatus status;

    @ManyToOne
    @JoinColumn(name = "MODEL_ID")
    private EstimationModel estimationModel;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PERIOD_FROM")
    private LocalDate periodFrom;

    @Column(name = "PERIOD_TO")
    private LocalDate periodTo;

    @Column(name = "ACTIVITY")
    private Integer activity;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        }, mappedBy = "estimationsForEvaluator")
    private List<Employee> employeesEvaluators;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        }, mappedBy = "estimationsForEvaluatee")
    private List<Employee> employeesEvaluatees;

    public Estimation() {
    }

    public Estimation(EstimationStatus status, EstimationModel estimationModel, String name, LocalDate periodFrom, LocalDate periodTo,
                      Integer activity, List<Employee> employeesEvaluators, List<Employee> employeesEvaluatees) {
        this.status = status;
        this.estimationModel = estimationModel;
        this.name = name;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.activity = activity;
        this.employeesEvaluators = employeesEvaluators;
        this.employeesEvaluatees = employeesEvaluatees;
    }

    public void addEmployeeEvaluator(Employee employee) {
        employeesEvaluators.add(employee);
        employee.getEstimationsForEvaluator().add(this);
    }

    public void addEmployeeEvaluatee(Employee employee) {
        employeesEvaluatees.add(employee);
        employee.getEstimationsForEvaluatee().add(this);
    }

    public void removeEmployeeEvaluator(Employee employee) {
        employeesEvaluators.remove(employee);
        employee.getEstimationsForEvaluator().remove(this);
    }


    public void removeEmployeeEvaluatee(Employee employee) {
        employeesEvaluatees.remove(employee);
        employee.getEstimationsForEvaluatee().remove(this);
    }

    public void removeEvaluators() {
        for (Employee employee : new ArrayList<>(employeesEvaluators)) {
            removeEmployeeEvaluator(employee);
        }
    }


    public void removeEvaluatees() {
        for (Employee employee : new ArrayList<>(employeesEvaluatees)) {
            removeEmployeeEvaluatee(employee);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstimationStatus getStatus() {
        return status;
    }

    public void setStatus(EstimationStatus status) {
        this.status = status;
    }

    public EstimationModel getEstimationModel() {
        return estimationModel;
    }

    public void setEstimationModel(EstimationModel estimationModel) {
        this.estimationModel = estimationModel;
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

    public List<Employee> getEmployeesEvaluators() {
        return employeesEvaluators;
    }

    public void setEmployeesEvaluators(List<Employee> employeesEvaluators) {
        this.employeesEvaluators = employeesEvaluators;
    }

    public List<Employee> getEmployeesEvaluatees() {
        return employeesEvaluatees;
    }

    public void setEmployeesEvaluatees(List<Employee> employeesEvaluatees) {
        this.employeesEvaluatees = employeesEvaluatees;
    }
}
