package hr.tvz.hrapp.domain;

import org.dozer.DozerBeanMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Base Service class with methods for mapping entity object or list of entity objects to DTO objects.
 *
 * @author vedrana.soljic
 */
public abstract class BaseService {

    private final DozerBeanMapper dozerBeanMapper;

    public BaseService(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    /**
     * Maps list of entities to list od DTOs.
     *
     * @param entities entities to map
     * @param dtoClass type of class to map to (DTO class!)
     * @param <T>      return type of DTO
     * @return list of mapped DTOs
     */
    public <T> List<T> mapEntitiesToDTO(List<?> entities, Class<T> dtoClass) {
        return entities.stream().map(entity -> mapEntityToDTO(entity, dtoClass)).collect(Collectors.toList());
    }

    public <T, W> W mapEntityToDTO(T entity, Class<W> dtoClass) {
        return dozerBeanMapper.map(entity, dtoClass);
    }
}
