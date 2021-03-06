package hr.tvz.hrapp.domain.estimation_status;

import java.io.Serializable;

/**
 * @author vedrana.soljic
 */
public class EstimationStatusDTO implements Serializable {

    private Long id;

    private String status;

    public EstimationStatusDTO() {
    }

    public EstimationStatusDTO(Long id, String status) {
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
