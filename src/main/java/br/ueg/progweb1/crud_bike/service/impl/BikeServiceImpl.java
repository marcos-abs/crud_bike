package br.ueg.progweb1.crud_bike.service.impl;

import br.ueg.progweb1.crud_bike.exceptions.BusinessLogicError;
import br.ueg.progweb1.crud_bike.exceptions.BusinessLogicException;
import br.ueg.progweb1.crud_bike.exceptions.DataException;
import br.ueg.progweb1.crud_bike.exceptions.MandatoryException;
import br.ueg.progweb1.crud_bike.repository.BikeRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BikeServiceImpl implements br.ueg.progweb1.crud_bike.service.BikeService {

    @Autowired
    private BikeRepository repository;
    public List<br.ueg.progweb1.crud_bike.model.Bike> listAll(){

        return repository.findAll();
    }

    @Override
    public br.ueg.progweb1.crud_bike.model.Bike create(br.ueg.progweb1.crud_bike.model.Bike dado) {
        prepareToCreate(dado);
        validateMandatoryFields(dado);
        validateMountainBike(dado);
        validateSpeedBike(dado);
        validateBusinessLogic(dado);
        validateBusinessLogicForInsert(dado);
        return repository.save(dado);
    }

    private void prepareToCreate(br.ueg.progweb1.crud_bike.model.Bike dado) {
        dado.setId(null);
        dado.setCreatedDate(LocalDate.now());
    }

    @Override
    public br.ueg.progweb1.crud_bike.model.Bike update(br.ueg.progweb1.crud_bike.model.Bike dataToUpdate){
        var dataDB = validateBikeIdExists(dataToUpdate.getId());
        validateMandatoryFields(dataToUpdate);
        validateMountainBike(dataToUpdate);
        validateSpeedBike(dataToUpdate);
        validateBusinessLogic(dataToUpdate);
        validateBusinessLogicForUpdate(dataToUpdate);
        updatedDataDBFromUpdate(dataToUpdate, dataDB);
        return repository.save(dataDB);
    }

    public List<br.ueg.progweb1.crud_bike.model.Bike> listOnlyMountainBikes() {
        Optional<List<br.ueg.progweb1.crud_bike.model.Bike>> listagem = repository.findOnlyMountainBikes();
        if(listagem.isPresent()){
            return listagem.get();
        }
        return new ArrayList<>();
    }

    private void updatedDataDBFromUpdate(br.ueg.progweb1.crud_bike.model.Bike dataToUpdate, br.ueg.progweb1.crud_bike.model.Bike dataDB) {
        dataDB.setPartNumber(dataToUpdate.getPartNumber());
        dataDB.setDescription(dataToUpdate.getDescription());
        dataDB.setSizeWheel(dataToUpdate.getSizeWheel());
        dataDB.setSizeFrame(dataToUpdate.getSizeFrame());
        dataDB.setIsMTB(dataToUpdate.getIsMTB());
    }

    @Override
    public br.ueg.progweb1.crud_bike.model.Bike getById(Long id){
        return this.validateBikeIdExists(id);
    }

    @Override
    public br.ueg.progweb1.crud_bike.model.Bike deleteById(Long id){
        br.ueg.progweb1.crud_bike.model.Bike bikeToRemove = this.validateBikeIdExists(id);
        this.repository.delete(bikeToRemove);
        return bikeToRemove;
    }

    private br.ueg.progweb1.crud_bike.model.Bike validateBikeIdExists(Long id) {
        boolean valid = true;
        br.ueg.progweb1.crud_bike.model.Bike dadoDB = null;

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

    private br.ueg.progweb1.crud_bike.model.Bike internalGetById(Long id){
        Optional<br.ueg.progweb1.crud_bike.model.Bike> byId = repository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    private void validateBusinessLogicForInsert(br.ueg.progweb1.crud_bike.model.Bike dado) {
        if(Strings.isEmpty(dado.getPartNumber())){
            throw new BusinessLogicException(BusinessLogicError.MANDATORY_FIELD_NOT_FOUND);
        }
        Optional<br.ueg.progweb1.crud_bike.model.Bike> registerNumber = repository.findByPartNumber(dado.getPartNumber());
        if(registerNumber.isPresent()){
            throw new BusinessLogicException(BusinessLogicError.ALREADY_EXISTS);
        }
    }

    private void validateMountainBike(br.ueg.progweb1.crud_bike.model.Bike dado) {
        if(dado.getIsMTB() == true){
            if(dado.getSizeFrame() < 14.5d || dado.getSizeFrame() > 21.0d){
                throw new BusinessLogicException(BusinessLogicError.INCORRECT_VALUES);
            }
            if(dado.getSizeWheel() < 26.0d || dado.getSizeWheel() > 29.0d){
                throw new BusinessLogicException(BusinessLogicError.INCORRECT_VALUES);
            }
        }
    }

    private void validateSpeedBike(br.ueg.progweb1.crud_bike.model.Bike dado) {
        if(dado.getIsMTB() == false){
            if(dado.getSizeFrame() < 46.0d || dado.getSizeFrame() > 59.0d){
                throw new BusinessLogicException(BusinessLogicError.INCORRECT_VALUES);
            }
            if(dado.getSizeWheel() != 29.0d || dado.getSizeWheel() > 700.0d){
                throw new BusinessLogicException(BusinessLogicError.INCORRECT_VALUES);
            }
        }
    }

    private void validateBusinessLogicForUpdate(br.ueg.progweb1.crud_bike.model.Bike dado) {
        if(dado.getId() <= 0L ){
            throw new BusinessLogicException(BusinessLogicError.INVALID_KEY);
        }
    }

    private void validateBusinessLogic(br.ueg.progweb1.crud_bike.model.Bike dado) {

    }

    private void validateMandatoryFields(br.ueg.progweb1.crud_bike.model.Bike dado) {
        if(Strings.isEmpty(dado.getDescription())){
            throw new MandatoryException("Campos obrigatórios não preenchidos");
        }
    }
}
