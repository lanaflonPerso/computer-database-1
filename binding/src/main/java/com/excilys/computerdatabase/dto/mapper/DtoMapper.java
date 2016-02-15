package com.excilys.computerdatabase.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Vincent Galloy
 *         The Interface DtoMapper.
 */
public interface DtoMapper<Model, Dto> {

    /**
     * Map from model.
     *
     * @param model the model
     * @return the dto
     */
    Dto mapFromModel(Model model);

    /**
     * Map to model.
     *
     * @param dto the dto
     * @return the model
     */
    Model mapToModel(Dto dto);

    /**
     * Map list from model.
     *
     * @param list the list
     * @return the list
     */
    default List<Dto> mapListFromModel(List<Model> list) {
        return list.stream().map(this::mapFromModel).collect(Collectors.toList());
    }

    /**
     * Map list to model.
     *
     * @param list the list
     * @return the list
     */
    default List<Model> mapListToModel(List<Dto> list) {
        return list.stream().map(this::mapToModel).collect(Collectors.toList());
    }

    /**
     * Gets the id.
     *
     * @param id the id
     * @return the id
     */
    default Long getId(String id) {
        if (id == null) {
            return null;
        } else {
            return Long.valueOf(id);
        }
    }
}
