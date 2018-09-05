package hr.tvz.hrapp.domain.group.mapper;

import hr.tvz.hrapp.domain.group.Group;
import hr.tvz.hrapp.domain.group.GroupDTO;

import java.util.List;

/**
 * @author vedrana.soljic
 */
public interface GroupMapper {

    GroupDTO mapToDto(Group group);

    Group reverse(GroupDTO dto);

    List<GroupDTO> mapListToDtoList(List<Group> groups);

    List<Group> mapDtoListToList(List<GroupDTO> groupDTOS);

}
