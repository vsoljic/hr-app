package hr.tvz.hrapp.domain.estimation_evaluator.service;

import hr.tvz.hrapp.domain.estimation_evaluator.EstimationEvaluator;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface EstimationEvaluatorService {

    List<EstimationEvaluator> getAllForEstimationId(Long estimationId);

    List<EstimationEvaluator> getAllForEvaluatorId(Long evaluatorId);

    void save(Long estimationId, Long evaluatorId);

}
