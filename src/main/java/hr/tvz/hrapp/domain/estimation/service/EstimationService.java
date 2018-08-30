package hr.tvz.hrapp.domain.estimation.service;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.estimation.EstimationDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface EstimationService {

    List<EstimationDTO> findAllEstimations();

    EstimationDTO createNewEstimation(EstimationDTO estimationDTO);

    EstimationDTO saveSelectedEstimation(EstimationDTO estimationDTO);

    EstimationDTO findById(Long id);

    List<EmployeeDTO> findAllEmployeesEvaluatorsById(Long id);

    List<EmployeeDTO> findAllEvaluateesByEvaluatorAndEstimation(Long id, Long evaluatorId);
}
