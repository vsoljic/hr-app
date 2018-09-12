package hr.tvz.hrapp.domain.group;


import hr.tvz.hrapp.domain.goal.GoalDTO;

import java.io.Serializable;
import java.util.List;


/**
 * @author vedrana.soljic
 */
public class GroupDTO implements Serializable {

    private Long id;

    private String name;

    private Long ponderPercentage;

    private List<GoalDTO> goals;

    private Long totalPonderForGroup;

    public GroupDTO() {
    }

    public GroupDTO(Long id, String name, Long ponderPercentage, List<GoalDTO> goals, Long totalPonderForGroup) {
        this.id = id;
        this.name = name;
        this.ponderPercentage = ponderPercentage;
        this.goals = goals;
        this.totalPonderForGroup = totalPonderForGroup;
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

    public List<GoalDTO> getGoals() {
        return goals;
    }

    public void setGoals(List<GoalDTO> goals) {
        this.goals = goals;
    }

    public Long getTotalPonderForGroup() {
        return totalPonderForGroup;
    }

    public void setTotalPonderForGroup(Long totalPonderForGroup) {
        this.totalPonderForGroup = totalPonderForGroup;
    }
}
