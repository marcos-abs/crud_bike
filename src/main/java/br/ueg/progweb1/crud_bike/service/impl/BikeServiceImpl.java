package br.ueg.progweb1.crud_bike.service.impl;

import br.ueg.progweb1.crud_bike.exceptions.BusinessLogicError;
import br.ueg.progweb1.crud_bike.exceptions.BusinessLogicException;
import br.ueg.progweb1.crud_bike.exceptions.DataException;
import br.ueg.progweb1.crud_bike.exceptions.MandatoryException;
import br.ueg.progweb1.crud_bike.repository.BikeRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ueg.progweb1.crud_bike.model.Bike;
import br.ueg.progweb1.crud_bike.service.BikeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BikeServiceImpl implements BikeService {

    @Autowired
    private BikeRepository repository;
    public List<Bike> listAll(){

        return repository.findAll();
    }

    @Override
    public Bike create(Bike dado) {
        prepareToCreate(dado);
        validateMandatoryFields(dado);
        validateMountainBike(dado);
        validateSpeedBike(dado);
        /*validateBusinessLogic(dado);*/
        validateBusinessLogicForInsert(dado);
        return repository.save(dado);
    }

    private void prepareToCreate(Bike dado) {
        dado.setId(null);
        dado.setCreatedDate(LocalDate.now());
    }

    @Override
    public Bike update(Bike dataToUpdate){
        var dataDB = validateBikeIdExists(dataToUpdate.getId());
        validateMandatoryFields(dataToUpdate);
        validateMountainBike(dataToUpdate);
        validateSpeedBike(dataToUpdate);
        validateBusinessLogic(dataToUpdate);
        validateBusinessLogicForUpdate(dataToUpdate);
        updatedDataDBFromUpdate(dataToUpdate, dataDB);
        return repository.save(dataDB);
    }

    public Bike patch(Bike dataToUpdate){
        var dataDB = validateBikeIdExists(dataToUpdate.getId());
        validateBusinessLogic(dataToUpdate);
        validateBusinessLogicForUpdate(dataToUpdate);
        patchedDataDBFromUpdate(dataToUpdate, dataDB);
        return repository.save(dataDB);
    }

    public List<Bike> listOnlyMountainBikes() {
        Optional<List<Bike>> listagem = repository.findOnlyMountainBikes();
        if(listagem.isPresent()){
            return listagem.get();
        }
        return new ArrayList<>();
    }

    private void updatedDataDBFromUpdate(Bike dataToUpdate, Bike dataDB) {
        dataDB.setPartNumber(dataToUpdate.getPartNumber());
        dataDB.setDescription(dataToUpdate.getDescription());
        dataDB.setSizeWheel(dataToUpdate.getSizeWheel());
        dataDB.setSizeFrame(dataToUpdate.getSizeFrame());
        dataDB.setIsMTB(dataToUpdate.getIsMTB());
    }

    private void patchedDataDBFromUpdate(Bike dataToUpdate, Bike dataDB) {
        if(Objects.nonNull(dataToUpdate.getPartNumber())){
            if(!dataToUpdate.getPartNumber().isEmpty()){
                dataDB.setPartNumber(dataToUpdate.getPartNumber());
            }
        }
        if(Objects.nonNull(dataToUpdate.getDescription())) {
            if(!dataToUpdate.getDescription().isEmpty()) {
                dataDB.setDescription(dataToUpdate.getDescription());
            }
        }
    }

    @Override
    public Bike getById(Long id){
        return this.validateBikeIdExists(id);
    }

    public Optional<List<Bike>> getByDescription(String description) {
        return repository.findByDescriptionContaining(description);
    }

    @Override
    public Bike deleteById(Long id){
        Bike bikeToRemove = this.validateBikeIdExists(id);
        this.repository.delete(bikeToRemove);
        return bikeToRemove;
    }

    private Bike validateBikeIdExists(Long id) {
        boolean valid = true;
        Bike dadoDB = null;

        if(Objects.nonNull(id)) {
            dadoDB = this.internalGetById(id);
            if (dadoDB == null) {
                valid = false;
            }
        } else {
            valid = false;
        }

        if(Boolean.FALSE.equals(valid)){
            throw new DataException("Modelo não encontrado");
        }

        return dadoDB;
    }

    private Bike internalGetById(Long id){
        Optional<Bike> byId = repository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    private void validateBusinessLogicForInsert(Bike dado) {
        if(Strings.isEmpty(dado.getPartNumber())){
            throw new BusinessLogicException(BusinessLogicError.MANDATORY_FIELD_NOT_FOUND);
        }
        Optional<Bike> registerNumber = repository.findByPartNumber(dado.getPartNumber());
        if(registerNumber.isPresent()){
            throw new BusinessLogicException(BusinessLogicError.ALREADY_EXISTS);
        }
    }

    private void validateMountainBike(Bike dado) {
        if(dado.getIsMTB()){
            if(dado.getSizeFrame() < 14.5d || dado.getSizeFrame() > 21.0d){
                throw new BusinessLogicException(BusinessLogicError.INCORRECT_VALUES);
            }
            if(dado.getSizeWheel() < 26.0d || dado.getSizeWheel() > 29.0d){
                throw new BusinessLogicException(BusinessLogicError.INCORRECT_VALUES);
            }
        }
    }

    private void validateSpeedBike(Bike dado) {
        if(!dado.getIsMTB()){
            if(dado.getSizeFrame() < 46.0d || dado.getSizeFrame() > 59.0d){
                throw new BusinessLogicException(BusinessLogicError.INCORRECT_VALUES);
            }
            if(dado.getSizeWheel() != 29.0d || dado.getSizeWheel() > 700.0d){
                throw new BusinessLogicException(BusinessLogicError.INCORRECT_VALUES);
            }
        }
    }

    private void validateBusinessLogicForUpdate(Bike dado) {
        if(dado.getId() <= 0L ){
            throw new BusinessLogicException(BusinessLogicError.INVALID_KEY);
        }
    }

    private void validateBusinessLogic(Bike dado) {
    }

    private void validateMandatoryFields(Bike dado) {
        if(Strings.isEmpty(dado.getDescription()) || Strings.isEmpty(dado.getPartNumber())){
            throw new MandatoryException("Campo de descrição é de preenchimento obrigatório");
        }
    }
}
