package hr.tvz.hrapp.domain.estimation_evaluator.service;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface EstimationEvaluatorService {

    void save(Long estimationId, Long evaluatorId);

}
