package hr.tvz.hrapp.domain.employee.service;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.estimation.Estimation;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface EmployeesService {

    EmployeeDTO findById(Long id);

    List<EmployeeDTO> getAllEmployees();

    List<EmployeeDTO> getAllEmployeesExceptSelectedOne(Long id);

    List<EmployeeDTO> getAllDistinctEvaluatorsForEstimation(List<Estimation> estimations);

    List<EmployeeDTO> findAllEvaluateesByEvaluatorAndEstimation(Long id, Long evaluatorId);

    List<EmployeeDTO> findNotConnectedEmployeesForEvaluator(Long evaluatorId, Long estimationId);

    void deleteSelectedEvaluatee(Long estimationId, Long evaluatorId, Long evaluateeId);
}
