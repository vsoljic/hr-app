package hr.tvz.hrapp.domain.relationship_est_employees;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author vedrana.soljic
 */
@Entity
@Table(name = "RELATIONSHIP_EST_EMPLOYEES")
public class RelationshipEstEmployees implements Serializable {

    @Id
    @Column(name = "ESTIMATION_ID")
    private Long estimationId;

    @Column(name = "EMPLOYEE_EVALUATOR_ID")
    private Long employeeEvaluatorId;

    @Column(name = "EMPLOYEE_EVALUATEE_ID")
    private Long employeeEvaluateeId;

    public RelationshipEstEmployees() {
    }

    public RelationshipEstEmployees(Long estimationId, Long employeeEvaluatorId, Long employeeEvaluateeId) {
        this.estimationId = estimationId;
        this.employeeEvaluatorId = employeeEvaluatorId;
        this.employeeEvaluateeId = employeeEvaluateeId;
    }

    public RelationshipEstEmployees(Long estimationId, Long employeeEvaluatorId) {
        this.estimationId = estimationId;
        this.employeeEvaluatorId = employeeEvaluatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelationshipCompositeKey)) return false;
        RelationshipCompositeKey that = (RelationshipCompositeKey) o;
        return Objects.equals(getEstimationId(), that.getEstimationId()) &&
            Objects.equals(getEmployeeEvaluatorId(), that.getEmployeeEvaluatorId()) &&
            Objects.equals(getEmployeeEvaluateeId(), that.getEmployeeEvaluateeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEstimationId(), getEmployeeEvaluatorId(), getEmployeeEvaluateeId());
    }

    public Long getEstimationId() {
        return estimationId;
    }

    public void setEstimationId(Long estimationId) {
        this.estimationId = estimationId;
    }

    public Long getEmployeeEvaluatorId() {
        return employeeEvaluatorId;
    }

    public void setEmployeeEvaluatorId(Long employeeEvaluatorId) {
        this.employeeEvaluatorId = employeeEvaluatorId;
    }

    public Long getEmployeeEvaluateeId() {
        return employeeEvaluateeId;
    }

    public void setEmployeeEvaluateeId(Long employeeEvaluateeId) {
        this.employeeEvaluateeId = employeeEvaluateeId;
    }
}
