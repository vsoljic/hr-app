package hr.tvz.hrapp.domain.estimation_evaluator.repository;

import hr.tvz.hrapp.domain.estimation_evaluator.EstimationEvaluator;
import hr.tvz.hrapp.domain.estimation_evaluator.EstimationEvaluatorCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Repository
public interface EstimationEvaluatorRepository extends JpaRepository<EstimationEvaluator, EstimationEvaluatorCompositeKey> {

    List<EstimationEvaluator> findDistinctByKey_EstimationId(Long estimationId);
}
