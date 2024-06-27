package br.ueg.progweb1.crud_bike.mapper;

import br.ueg.progweb1.crud_bike.model.GenericModel;
import org.mapstruct.MappingTarget;

public interface GenericUpdateMapper<
        MODEL extends GenericModel<TYPE_PK>,
        TYPE_PK
        > {
    /**
     * Atualiza o objeto entity com os dados
     * do objeto updateEntity, pegando apenas o atributos
     * preenchidos.
     * @param entity
     * @param updateEntity
     */
    void updateModelFromModel(@MappingTarget MODEL entity, MODEL updateEntity);
}
