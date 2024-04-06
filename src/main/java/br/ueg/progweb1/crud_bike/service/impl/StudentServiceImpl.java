package br.ueg.progweb1.aula01.service.impl;

import br.ueg.progweb1.aula01.exceptions.BusinessLogicError;
import br.ueg.progweb1.aula01.exceptions.BusinessLogicException;
import br.ueg.progweb1.aula01.exceptions.DataException;
import br.ueg.progweb1.aula01.exceptions.MandatoryException;
import br.ueg.progweb1.aula01.model.Student;
import br.ueg.progweb1.aula01.repository.StudentRepository;
import br.ueg.progweb1.aula01.service.StudentService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;
    public List<Student> listAll(){

        return repository.findAll();
    }

    @Override
    public Student create(Student dado) {
        prepareToCreate(dado);
        validateMandatoryFields(dado);
        validateBusinessLogic(dado);
        validateBusinessLogicForInsert(dado);
        return repository.save(dado);
    }

    private void prepareToCreate(Student dado) {
        dado.setId(null);
        dado.setCreatedDate(LocalDate.now());
    }

    @Override
    public Student update(Student dataToUpdate){
        var dataDB = validateStudentIdExists(dataToUpdate.getId());
        validateMandatoryFields(dataToUpdate);
        validateBusinessLogic(dataToUpdate);
        validateBusinessLogicForUpdate(dataToUpdate);
        updatedDataDBFromUpdate(dataToUpdate, dataDB);
        return repository.save(dataDB);
    }

    @Override
    public List<Student> listYesterdayRegisters() {
        Optional<List<Student>> listagem = repository.findYesterdayRegisters();
        if(listagem.isPresent()){
            return listagem.get();
        }
        return new ArrayList<>();
    }

    private void updatedDataDBFromUpdate(Student dataToUpdate, Student dataDB) {
        dataDB.setName(dataToUpdate.getName());
        dataDB.setCourse(dataToUpdate.getCourse());
    }

    @Override
    public Student getById(Long id){
        return this.validateStudentIdExists(id);
    }

    @Override
    public Student deleteById(Long id){
        Student studentToRemove = this.validateStudentIdExists(id);
        this.repository.delete(studentToRemove);
        return studentToRemove;
    }

    private Student validateStudentIdExists(Long id) {
        boolean valid = true;
        Student dadoDB = null;

        if(Objects.nonNull(id)) {
            dadoDB = this.internalGetById(id);
            if (dadoDB == null) {
                valid = false;
            }
        } else {
            valid = false;
        }

        if(Boolean.FALSE.equals(valid)){
            throw new DataException("Estudante não encontrado");
        }

        return dadoDB;
    }

    private Student internalGetById(Long id){
        Optional<Student> byId = repository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    private void validateBusinessLogicForInsert(Student dado) {
        if(Strings.isEmpty(dado.getRegisterNumber())){
            throw new BusinessLogicException(BusinessLogicError.MANDATORY_FIELD_NOT_FOUND);
        }
        Optional<Student> registerNumber = repository.findByRegisterNumber(dado.getRegisterNumber());
        if(registerNumber.isPresent()){
            throw new BusinessLogicException(BusinessLogicError.ALREADY_EXISTS);
        }
    }

    private void validateBusinessLogicForUpdate(Student dado) {
        if(dado.getId() <= 0L ){
            throw new BusinessLogicException(BusinessLogicError.INVALID_KEY);
        }
    }

    private void validateBusinessLogic(Student dado) {

    }

    private void validateMandatoryFields(Student dado) {
        if(Strings.isEmpty(dado.getName())){
            throw new MandatoryException("Campos obrigatórios não preenchidos");
        }
    }
}
