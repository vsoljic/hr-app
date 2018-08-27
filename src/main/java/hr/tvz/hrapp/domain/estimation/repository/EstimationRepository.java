package hr.tvz.hrapp.domain.estimation.repository;

import hr.tvz.hrapp.domain.estimation.Estimation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vedrana.soljic
 */
@Repository
public interface EstimationRepository extends JpaRepository<Estimation, Long> {
}
