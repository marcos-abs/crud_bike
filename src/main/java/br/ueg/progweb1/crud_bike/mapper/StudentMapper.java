package br.ueg.progweb1.aula01.mapper;

import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public br.ueg.progweb1.aula01.model.Bike toModel(br.ueg.progweb1.aula01.model.dtos.CreateBikeDTO dto) {
        br.ueg.progweb1.aula01.model.Bike s = new br.ueg.progweb1.aula01.model.Bike();
        s.setCourse(dto.getCourse());
        s.setName(dto.getName());
        s.setRegisterNumber(dto.getRegisterNumber());
        return s;
    }

    public br.ueg.progweb1.aula01.model.Bike toModel(br.ueg.progweb1.aula01.model.dtos.UpdateBikeDTO dto) {
        br.ueg.progweb1.aula01.model.Bike s = new br.ueg.progweb1.aula01.model.Bike();
        s.setCourse(dto.getCourse());
        s.setName(dto.getName());
        return s;
    }
}
