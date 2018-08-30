package hr.tvz.hrapp.domain.employee;

import hr.tvz.hrapp.domain.User;
import hr.tvz.hrapp.domain.estimation.Estimation;
import hr.tvz.hrapp.domain.estimation.EstimationDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @author vedrana.soljic
 */
public class EmployeeDTO implements Serializable {

    private Long id;

    private User user;

    private Long employeeCode;

    private String firstName;

    private String lastName;

    private String workPosition;

    private List<EstimationDTO> estimationsForEvaluator;

    private List<EstimationDTO> estimationsForEvaluatee;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, User user, Long employeeCode, String firstName, String lastName, String workPosition,
                       List<EstimationDTO> estimationsForEvaluator, List<EstimationDTO> estimationsForEvaluatee) {
        this.id = id;
        this.user = user;
        this.employeeCode = employeeCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.workPosition = workPosition;
        this.estimationsForEvaluator = estimationsForEvaluator;
        this.estimationsForEvaluatee = estimationsForEvaluatee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Long employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public List<EstimationDTO> getEstimationsForEvaluator() {
        return estimationsForEvaluator;
    }

    public void setEstimationsForEvaluator(List<EstimationDTO> estimationsForEvaluator) {
        this.estimationsForEvaluator = estimationsForEvaluator;
    }

    public List<EstimationDTO> getEstimationsForEvaluatee() {
        return estimationsForEvaluatee;
    }

    public void setEstimationsForEvaluatee(List<EstimationDTO> estimationsForEvaluatee) {
        this.estimationsForEvaluatee = estimationsForEvaluatee;
    }
}
