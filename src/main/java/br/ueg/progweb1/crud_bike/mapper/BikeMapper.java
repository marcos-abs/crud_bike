package br.ueg.progweb1.crud_bike.mapper;

import org.springframework.stereotype.Component;

@Component
public class BikeMapper {
    public br.ueg.progweb1.crud_bike.model.Bike toModel(br.ueg.progweb1.crud_bike.model.dtos.CreateBikeDTO dto) {
        br.ueg.progweb1.crud_bike.model.Bike b = new br.ueg.progweb1.crud_bike.model.Bike();
        b.setPartNumber(dto.getPartNumber());
        b.setDescription(dto.getDescription());
        b.setSizeFrame(dto.getSizeFrame());
        b.setSizeWheel(dto.getSizeWheel());
        b.setIsMTB(dto.getIsMTB());
        return b;

    }

    public br.ueg.progweb1.crud_bike.model.Bike toModel(br.ueg.progweb1.crud_bike.model.dtos.UpdateBikeDTO dto) {
        br.ueg.progweb1.crud_bike.model.Bike b = new br.ueg.progweb1.crud_bike.model.Bike();
        b.setPartNumber(dto.getPartNumber());
        b.setDescription(dto.getDescription());
        b.setSizeFrame(dto.getSizeFrame());
        b.setSizeWheel(dto.getSizeWheel());
        b.setIsMTB(dto.getIsMTB());
        return b;
    }
}
