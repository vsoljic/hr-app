package hr.tvz.hrapp.domain.group;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author vedrana.soljic
 */
@Entity
@Table(name ="GROUP_OF_GOALS")
public class Group implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PONDER_PERCENTAGE")
    private Long ponderPercentage;

    public Group() {
    }

    public Group(Long id, String name, Long ponderPercentage) {
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
