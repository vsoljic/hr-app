package hr.tvz.hrapp.domain.model.repository;

import hr.tvz.hrapp.domain.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vedrana.soljic
 */
@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}
