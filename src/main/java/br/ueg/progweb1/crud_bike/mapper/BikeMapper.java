package br.ueg.progweb1.crud_bike.mapper;

import br.ueg.progweb1.crud_bike.model.Bike;
import br.ueg.progweb1.crud_bike.model.dtos.BikeDTO;
import br.ueg.progweb1.crud_bike.model.dtos.CreateBikeDTO;
import br.ueg.progweb1.crud_bike.model.dtos.UpdateBikeDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, //checa se o valor é nulo antes de setar
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE //se o valor não for passado não faz nada.
)
public interface BikeMapper extends GenericMapper<
        BikeDTO, // DTO Geral
        CreateBikeDTO, // DTO Create
        UpdateBikeDTO, // DTO Update
        BikeDTO, // DTO List
        Bike, // Model
        Long // PK_TYPE
        > {

    @Override
    Bike toModel(BikeDTO dto);

    @Override
    Bike fromModelUpdateToModel(UpdateBikeDTO bikeDTO);

    @Override
    BikeDTO toDTO(Bike model);

    @Override
    @Named(value = "toDTOList") // para identificar o nome desse metodo pelo mapstruct
    BikeDTO toDTOList(Bike model);

    @Override
    @IterableMapping(qualifiedByName = "toDTOList") // para orientar qual metodo utilizar no caso de vários target=source;
    List<BikeDTO> fromModelToDTOList(List<Bike> bikes);

    @Override
    void updateModelFromModel(@MappingTarget Bike entity, Bike updateEntity);
}

