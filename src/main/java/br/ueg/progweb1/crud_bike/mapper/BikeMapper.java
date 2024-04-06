package br.ueg.progweb1.crud_bike.mapper;

import org.springframework.stereotype.Component;

@Component
public class BikeMapper {
    public br.ueg.progweb1.crud_bike.model.Bike toModel(br.ueg.progweb1.crud_bike.model.dtos.CreateBikeDTO dto) {
        br.ueg.progweb1.crud_bike.model.Bike s = new br.ueg.progweb1.crud_bike.model.Bike();
        s.setSerialNumber(dto.getSerialNumber());
        s.setDescription(dto.getDescription());
        s.setIsMTB(dto.getIsMTB());
        return s;
    }

    public br.ueg.progweb1.crud_bike.model.Bike toModel(br.ueg.progweb1.crud_bike.model.dtos.UpdateBikeDTO dto) {
        br.ueg.progweb1.crud_bike.model.Bike s = new br.ueg.progweb1.crud_bike.model.Bike();
        s.setDescription(dto.getDescription());
        s.setIsMTB(dto.getIsMTB());
        return s;
    }
}
