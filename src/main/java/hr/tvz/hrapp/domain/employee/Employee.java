package hr.tvz.hrapp.domain.employee;

import hr.tvz.hrapp.domain.User;
import hr.tvz.hrapp.domain.estimation.Estimation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author vedrana.soljic
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "EMPLOYEE_CODE")
    private Long employeeCode;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "WORK_POSITION")
    private String workPosition;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
    @JoinTable(name = "RELATIONSHIP_EST_EMPLOYEES",
        joinColumns = @JoinColumn(name = "EMPLOYEE_EVALUATOR_ID"),
        inverseJoinColumns = @JoinColumn(name = "ESTIMATION_ID"))
    private List<Estimation> estimationsForEvaluator;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
    @JoinTable(name = "RELATIONSHIP_EST_EMPLOYEES",
        joinColumns = @JoinColumn(name = "EMPLOYEE_EVALUATEE_ID"),
        inverseJoinColumns = @JoinColumn(name = "ESTIMATION_ID"))
    private List<Estimation> estimationsForEvaluatee;

    public Employee() {
    }

    public Employee(Long id, User user, Long employeeCode, String firstName, String lastName, String workPosition,
                    List<Estimation> estimationsForEvaluator, List<Estimation> estimationsForEvaluatee) {
        this.id = id;
        this.user = user;
        this.employeeCode = employeeCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.workPosition = workPosition;
        this.estimationsForEvaluator = estimationsForEvaluator;
        this.estimationsForEvaluatee = estimationsForEvaluatee;
        /*  this.goals = goals;*/
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Long employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public List<Estimation> getEstimationsForEvaluator() {
        return estimationsForEvaluator;
    }

    public void setEstimationsForEvaluator(List<Estimation> estimationsForEvaluator) {
        this.estimationsForEvaluator = estimationsForEvaluator;
    }

    public List<Estimation> getEstimationsForEvaluatee() {
        return estimationsForEvaluatee;
    }

    public void setEstimationsForEvaluatee(List<Estimation> estimationsForEvaluatee) {
        this.estimationsForEvaluatee = estimationsForEvaluatee;
    }
}
