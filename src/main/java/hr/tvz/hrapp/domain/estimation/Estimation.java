package hr.tvz.hrapp.domain.estimation;

import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.estimation_status.EstimationStatus;
import hr.tvz.hrapp.domain.model.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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
    private Model model;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PERIOD_FROM")
    private LocalDate periodFrom;

    @Column(name = "PERIOD_TO")
    private LocalDate periodTo;

    @ManyToMany(mappedBy = "estimationsForEvaluator")
    private List<Employee> employeesEvaluators;

    @ManyToMany(mappedBy = "estimationsForEvaluatee")
    private List<Employee> employeesEvaluatees;

    public Estimation() {
    }

    public Estimation(EstimationStatus status, Model model, String name, LocalDate periodFrom, LocalDate periodTo,
                      List<Employee> employeesEvaluators, List<Employee> employeesEvaluatees) {
        this.status = status;
        this.model = model;
        this.name = name;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.employeesEvaluators = employeesEvaluators;
        this.employeesEvaluatees = employeesEvaluatees;
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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
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
