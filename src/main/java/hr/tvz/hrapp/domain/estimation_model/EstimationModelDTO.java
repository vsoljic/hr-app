package hr.tvz.hrapp.domain.estimation_model;

import hr.tvz.hrapp.domain.group.GroupDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vedrana.soljic
 */
public class EstimationModelDTO implements Serializable {

    private Long id;

    private String name;

    private List<GroupDTO> groups = new ArrayList<>();

    public EstimationModelDTO() {
    }

    public EstimationModelDTO(Long id, String name, List<GroupDTO> groups) {
        this.id = id;
        this.name = name;
        this.groups = groups;
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

    public List<GroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDTO> groups) {
        this.groups = groups;
    }
}
