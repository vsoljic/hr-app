package hr.tvz.hrapp.domain.group.mapper;

import hr.tvz.hrapp.domain.group.Group;
import hr.tvz.hrapp.domain.group.GroupDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vedrana.soljic
 */
@Component
public class GroupMapperImpl implements GroupMapper {

    @Override
    public GroupDTO mapToDto(Group group) {
        GroupDTO dto = new GroupDTO();

        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setPonderPercentage(group.getPonderPercentage());

        return dto;
    }

    @Override
    public Group reverse(GroupDTO dto) {
        Group group = new Group();

        group.setId(dto.getId());
        group.setName(dto.getName());
        group.setPonderPercentage(dto.getPonderPercentage());

        return group;
    }

    @Override
    public List<GroupDTO> mapListToDtoList(List<Group> groups) {
        List<GroupDTO> result = new ArrayList<>();
        groups.stream().forEach(i -> result.add(mapToDto(i)));

        return result;
    }

    @Override
    public List<Group> mapDtoListToList(List<GroupDTO> groupDTOS) {
        List<Group> result = new ArrayList<>();
        groupDTOS.stream().forEach(i -> result.add(reverse(i)));

        return result;
    }
}
