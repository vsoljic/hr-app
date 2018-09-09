package hr.tvz.hrapp.domain.estimation_evaluator;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author vedrana.soljic
 */
@Embeddable
public class EstimationEvaluatorCompositeKey implements Serializable {

    @Column(name = "ID_ESTIMATION")
    private Long estimationId;

    @Column(name = "ID_EVALUATOR")
    private Long evaluatorId;

    public EstimationEvaluatorCompositeKey() {
    }

    public EstimationEvaluatorCompositeKey(Long estimationId, Long evaluatorId) {
        this.estimationId = estimationId;
        this.evaluatorId = evaluatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstimationEvaluatorCompositeKey)) return false;
        EstimationEvaluatorCompositeKey that = (EstimationEvaluatorCompositeKey) o;
        return Objects.equals(getEstimationId(), that.getEstimationId()) &&
            Objects.equals(getEvaluatorId(), that.getEvaluatorId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEstimationId(), getEvaluatorId());
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
}
