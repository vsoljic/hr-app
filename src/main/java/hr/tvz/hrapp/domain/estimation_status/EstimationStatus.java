package hr.tvz.hrapp.domain.estimation_status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author vedrana.soljic
 */
@Entity
@Table(name = "EST_STATUS")
public class EstimationStatus implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "STATUS")
    private String status;

    public EstimationStatus() {
    }

    public EstimationStatus(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
