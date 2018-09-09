package hr.tvz.hrapp.domain.estimation_evaluator;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author vedrana.soljic
 */
@Entity
@Table(name = "ESTIMATION_EVALUATOR")
public class EstimationEvaluator implements Serializable {

    @EmbeddedId
    private EstimationEvaluatorCompositeKey key;

    public EstimationEvaluator() {
    }

    public EstimationEvaluator(EstimationEvaluatorCompositeKey key) {
        this.key = key;
    }

    public EstimationEvaluatorCompositeKey getKey() {
        return key;
    }

    public void setKey(EstimationEvaluatorCompositeKey key) {
        this.key = key;
    }
}
