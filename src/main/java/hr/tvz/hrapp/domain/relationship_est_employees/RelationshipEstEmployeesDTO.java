package hr.tvz.hrapp.domain.relationship_est_employees;

import java.io.Serializable;
import java.util.List;

/**
 * @author vedrana.soljic
 */
public class RelationshipEstEmployeesDTO implements Serializable {

    private Long estimationId;

    private Long evaluatorId;

    private List<Long> evaluateeIdList;

    public RelationshipEstEmployeesDTO() {
    }

    public RelationshipEstEmployeesDTO(Long estimationId, Long evaluatorId, List<Long> evaluateeIdList) {
        this.estimationId = estimationId;
        this.evaluatorId = evaluatorId;
        this.evaluateeIdList = evaluateeIdList;
    }

    public Long getEstimationId() {
        return estimationId;
    }

    public void setEstimationId(Long estimationId) {
        this.estimationId = estimationId;
    }

    public Long getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(Long evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public List<Long> getEvaluateeIdList() {
        return evaluateeIdList;
    }

    public void setEvaluateeIdList(List<Long> evaluateeIdList) {
        this.evaluateeIdList = evaluateeIdList;
    }
}
