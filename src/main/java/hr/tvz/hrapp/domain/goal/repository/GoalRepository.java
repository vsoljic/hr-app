package hr.tvz.hrapp.domain.goal.repository;

import hr.tvz.hrapp.domain.goal.Goal;
import hr.tvz.hrapp.domain.goal.GoalDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    List<GoalDTO> findAllByEstimationIdAndGroupIdAndEmployeeId(Long estimationId, Long groupId, Long employeeId);
}
