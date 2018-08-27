package hr.tvz.hrapp.domain.group.service;

import hr.tvz.hrapp.domain.BaseService;
import hr.tvz.hrapp.domain.group.GroupDTO;
import hr.tvz.hrapp.domain.group.repository.GroupRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vedrana.soljic
 */
@Service
public class GroupServiceImpl extends BaseService implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(DozerBeanMapper dozerBeanMapper, GroupRepository groupRepository) {
        super(dozerBeanMapper);
        this.groupRepository = groupRepository;
    }

    @Override
    public List<GroupDTO> findAllGroups() {
        return mapEntitiesToDTO(groupRepository.findAll(), GroupDTO.class);
    }
}
