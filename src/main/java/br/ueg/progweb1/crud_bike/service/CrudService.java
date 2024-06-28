package br.ueg.progweb1.crud_bike.service;

import br.ueg.progweb1.crud_bike.model.GenericModel;

import java.util.List;
import java.util.Optional;

public interface CrudService<
        MODEL extends GenericModel<TYPE_PK>, TYPE_PK
        > {
    List<MODEL> listAll();
    MODEL create(MODEL dado);

    MODEL update(MODEL dado);

    MODEL getById(TYPE_PK id);

    List<MODEL> getByDescription(String description);

    MODEL deleteById(TYPE_PK id);
}
