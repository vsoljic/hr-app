package hr.tvz.hrapp.domain.estimation_evaluator.service;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.employee.mapper.EmployeeMapper;
import hr.tvz.hrapp.domain.employee.service.EmployeesService;
import hr.tvz.hrapp.domain.estimation_evaluator.EstimationEvaluator;
import hr.tvz.hrapp.domain.estimation_evaluator.EstimationEvaluatorCompositeKey;
import hr.tvz.hrapp.domain.estimation_evaluator.repository.EstimationEvaluatorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class EstimationEvaluatorServiceImpl implements EstimationEvaluatorService {

    private final EstimationEvaluatorRepository repository;
    public EstimationEvaluatorServiceImpl(EstimationEvaluatorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Long estimationId, Long evaluatorId) {

        EstimationEvaluator estimationEvaluator = new EstimationEvaluator(new EstimationEvaluatorCompositeKey(estimationId, evaluatorId));
        repository.save(estimationEvaluator);

    }
}
