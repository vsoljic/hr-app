package hr.tvz.hrapp.domain.goal.repository;

import hr.tvz.hrapp.domain.goal.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vedrana.soljic
 */
@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
}
