package hr.tvz.hrapp.domain.estimation.service;

import hr.tvz.hrapp.domain.employee.EmployeeDTO;
import hr.tvz.hrapp.domain.estimation.EstimationDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface EstimationService {

    List<EstimationDTO> findAllEstimations();

    EstimationDTO findById(Long id);

    List<EstimationDTO> findAllByLoggedInUser(Long id);

    EstimationDTO createNewEstimation(EstimationDTO estimationDTO);

    EstimationDTO saveSelectedEstimation(EstimationDTO estimationDTO);

    List<EmployeeDTO> findAllEmployeesEvaluatorsByEstimationId(Long id);

}
