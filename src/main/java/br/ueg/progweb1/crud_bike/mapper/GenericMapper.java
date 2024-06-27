package br.ueg.progweb1.crud_bike.mapper;

import br.ueg.progweb1.aula01.model.GenericModel;
import br.ueg.progweb1.aula01.mapper.GenericUpdateMapper;
import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.util.List;

public interface GenericMapper<
        DTO,
        DTOCreate,
        DTOUpdate,
        DTOList,
        MODEL extends GenericModel<TYPE_PK>,
        TYPE_PK
        > extends GenericUpdateMapper<MODEL, TYPE_PK> {
    MODEL toModel(DTO dto);
    MODEL fromModelCreateToModel(DTOCreate dtoCreate);

    MODEL fromModelUpdateToModel(DTOUpdate dtoUpdate);



    DTO toDTO(MODEL model);

    @Named(value = "toDTOList") // para identificar o nome desse metodo pelo mapstruct
    DTOList toDTOList(MODEL model);

    @IterableMapping(qualifiedByName = "toDTOList") // para orientar qual metodo utilizar no caso de vários target=source;
    List<DTOList> fromModelToDTOList(List<MODEL> modelList);
}
