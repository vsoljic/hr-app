package hr.tvz.hrapp.domain.estimation_status.mapper;

import hr.tvz.hrapp.domain.estimation_status.EstimationStatus;
import hr.tvz.hrapp.domain.estimation_status.EstimationStatusDTO;
import org.springframework.stereotype.Component;

/**
 * @author vedrana.soljic
 */
@Component
public class EstimationStatusMapperImpl implements EstimationStatusMapper {

    @Override
    public EstimationStatusDTO mapToDto(EstimationStatus status) {

        EstimationStatusDTO dto = new EstimationStatusDTO();
        dto.setId(status.getId());
        dto.setStatus(status.getStatus());

        return dto;
    }

    @Override
    public EstimationStatus reverse(EstimationStatusDTO dto) {

        EstimationStatus status = new EstimationStatus();
        status.setId(dto.getId());
        status.setStatus(dto.getStatus());

        return status;
    }
}
