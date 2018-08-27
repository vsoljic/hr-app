package hr.tvz.hrapp.domain.estimation_status.repository;

import hr.tvz.hrapp.domain.estimation_status.EstimationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vedrana.soljic
 */
@Repository
public interface EstimationStatusRepository extends JpaRepository<EstimationStatus, Long> {
}
