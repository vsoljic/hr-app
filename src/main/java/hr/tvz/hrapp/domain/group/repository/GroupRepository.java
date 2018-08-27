package hr.tvz.hrapp.domain.group.repository;

import hr.tvz.hrapp.domain.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vedrana.soljic
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
