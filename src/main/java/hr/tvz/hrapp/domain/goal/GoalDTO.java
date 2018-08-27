package hr.tvz.hrapp.domain.goal;

import hr.tvz.hrapp.domain.employee.Employee;
import hr.tvz.hrapp.domain.estimation.Estimation;
import hr.tvz.hrapp.domain.group.Group;

import java.io.Serializable;

/**
 * @author vedrana.soljic
 */
public class GoalDTO implements Serializable {
    private Long id;

    private String name;

    private Long ponderPercentage;

    private Long targetValue;

    private Long achievementPercentage;

    private Group group;

    private Employee employee;

    private Estimation estimation;

    public GoalDTO() {
    }

    public GoalDTO(Long id, String name, Long ponderPercentage, Long targetValue, Long achievementPercentage, Group group,
                   Employee employee, Estimation estimation) {
        this.id = id;
        this.name = name;
        this.ponderPercentage = ponderPercentage;
        this.targetValue = targetValue;
        this.achievementPercentage = achievementPercentage;
        this.group = group;
        this.employee = employee;
        this.estimation = estimation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPonderPercentage() {
        return ponderPercentage;
    }

    public void setPonderPercentage(Long ponderPercentage) {
        this.ponderPercentage = ponderPercentage;
    }

    public Long getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(Long targetValue) {
        this.targetValue = targetValue;
    }

    public Long getAchievementPercentage() {
        return achievementPercentage;
    }

    public void setAchievementPercentage(Long achievementPercentage) {
        this.achievementPercentage = achievementPercentage;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Estimation getEstimation() {
        return estimation;
    }

    public void setEstimation(Estimation estimation) {
        this.estimation = estimation;
    }
}
