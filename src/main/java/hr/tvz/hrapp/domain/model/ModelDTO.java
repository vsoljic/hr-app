package hr.tvz.hrapp.domain.model;

import hr.tvz.hrapp.domain.group.Group;

import java.io.Serializable;
import java.util.List;

/**
 * @author vedrana.soljic
 */
public class ModelDTO implements Serializable {

    private Long id;

    private String name;

    private List<Group> groups;

    public ModelDTO() {
    }

    public ModelDTO(Long id, String name, List<Group> groups) {
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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
