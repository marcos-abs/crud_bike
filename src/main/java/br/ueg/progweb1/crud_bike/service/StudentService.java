package br.ueg.progweb1.aula01.service;

import java.util.List;

public interface StudentService {

    List<br.ueg.progweb1.aula01.model.Bike> listAll();

    br.ueg.progweb1.aula01.model.Bike create(br.ueg.progweb1.aula01.model.Bike dado);

    br.ueg.progweb1.aula01.model.Bike update(br.ueg.progweb1.aula01.model.Bike dado);

    List<br.ueg.progweb1.aula01.model.Bike> listYesterdayRegisters();

    br.ueg.progweb1.aula01.model.Bike getById(Long id);

    br.ueg.progweb1.aula01.model.Bike deleteById(Long id);
}
