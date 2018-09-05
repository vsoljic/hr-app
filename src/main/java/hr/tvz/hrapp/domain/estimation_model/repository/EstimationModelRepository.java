package hr.tvz.hrapp.domain.estimation_model.repository;

import hr.tvz.hrapp.domain.estimation_model.EstimationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vedrana.soljic
 */
@Repository
public interface EstimationModelRepository extends JpaRepository<EstimationModel, Long> {

}
