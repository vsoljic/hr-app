package hr.tvz.hrapp.domain.model;

import hr.tvz.hrapp.domain.group.Group;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author vedrana.soljic
 */
@Entity
@Table(name = "MODEL")
public class Model implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(name = "MODEL_GROUP",
        joinColumns = {@JoinColumn(name = "MODEL_ID")},
        inverseJoinColumns = {@JoinColumn(name = "GROUP_ID")}
    )
    private List<Group> groups;

    public Model() {
    }

    public Model(Long id, String name, List<Group> groups) {
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
