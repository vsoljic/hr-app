package hr.tvz.hrapp.domain.estimation_model.mapper;

import hr.tvz.hrapp.domain.estimation_model.EstimationModel;
import hr.tvz.hrapp.domain.estimation_model.EstimationModelDTO;
import hr.tvz.hrapp.domain.group.mapper.GroupMapper;
import org.springframework.stereotype.Component;

/**
 * @author vedrana.soljic
 */
@Component
public class EstimationModelMapperImpl implements EstimationModelMapper {

    private final GroupMapper groupMapper;

    public EstimationModelMapperImpl(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Override
    public EstimationModelDTO mapToDto(EstimationModel estimationModel) {
        EstimationModelDTO dto = new EstimationModelDTO();
        dto.setId(estimationModel.getId());
        dto.setName(estimationModel.getName());
        dto.setGroups(groupMapper.mapListToDtoList(estimationModel.getGroups()));

        return dto;
    }

    @Override
    public EstimationModel reverse(EstimationModelDTO dto) {
        EstimationModel estimationModel = new EstimationModel();

        estimationModel.setId(dto.getId());
        estimationModel.setName(dto.getName());
        estimationModel.setGroups(groupMapper.mapDtoListToList(dto.getGroups()));

        return estimationModel;
    }
}
