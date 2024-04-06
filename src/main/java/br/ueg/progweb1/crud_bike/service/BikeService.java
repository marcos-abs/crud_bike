package br.ueg.progweb1.crud_bike.service;

import java.util.List;

public interface BikeService {

    List<br.ueg.progweb1.crud_bike.model.Bike> listAll();

    br.ueg.progweb1.crud_bike.model.Bike create(br.ueg.progweb1.crud_bike.model.Bike dado);

    br.ueg.progweb1.crud_bike.model.Bike update(br.ueg.progweb1.crud_bike.model.Bike dado);

    List<br.ueg.progweb1.crud_bike.model.Bike> listOnlyMTB();

    br.ueg.progweb1.crud_bike.model.Bike getById(Long id);

    br.ueg.progweb1.crud_bike.model.Bike deleteById(Long id);
}
