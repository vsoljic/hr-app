package hr.tvz.hrapp.domain.group;

import java.io.Serializable;

/**
 * @author vedrana.soljic
 */
public class GroupDTO implements Serializable {

    private Long id;

    private String name;

    private Long ponderPercentage;

    public GroupDTO() {
    }

    public GroupDTO(Long id, String name, Long ponderPercentage) {
        this.id = id;
        this.name = name;
        this.ponderPercentage = ponderPercentage;
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
}
