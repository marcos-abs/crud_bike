package br.ueg.progweb1.crud_bike.controllers;

import br.ueg.progweb1.crud_bike.mapper.BikeMapper;
import br.ueg.progweb1.crud_bike.model.Bike;
import br.ueg.progweb1.crud_bike.model.dtos.BikeDTO;
import br.ueg.progweb1.crud_bike.model.dtos.CreateBikeDTO;
import br.ueg.progweb1.crud_bike.model.dtos.UpdateBikeDTO;
import br.ueg.progweb1.crud_bike.service.BikeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api.version}/bike")
@CrossOrigin(origins = "http://localhost:4200")
public class BikeController extends
        GenericCRUDController<
                BikeDTO, //DTO Geral
                CreateBikeDTO, //DTO Create
                UpdateBikeDTO, //DTO Update
                BikeDTO, //DTO List
                Bike, // Modelo
                Long, // PK_TYPE
                BikeService, //Interface Serviço
                BikeMapper> // Mapper
    { }
