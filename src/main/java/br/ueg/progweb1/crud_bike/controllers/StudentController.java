package br.ueg.progweb1.aula01.controllers;

import br.ueg.progweb1.aula01.exceptions.BusinessLogicException;
import br.ueg.progweb1.aula01.exceptions.DataException;
import br.ueg.progweb1.aula01.exceptions.MandatoryException;
import br.ueg.progweb1.aula01.mapper.StudentMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "${api.version}/student")
public class StudentController {

    @Autowired
    private br.ueg.progweb1.aula01.service.BikeService service;

    @Autowired
    private StudentMapper mapper;

    @PostMapping
    @Operation(description = "End point para inclusão de aluno")
    public ResponseEntity<Object> create(@RequestBody br.ueg.progweb1.aula01.model.dtos.CreateBikeDTO dto){
        br.ueg.progweb1.aula01.model.Bike studentSaved =  null;
        try{
            br.ueg.progweb1.aula01.model.Bike studentModel = mapper.toModel(dto);
            studentSaved = service.create(studentModel);
        }catch (MandatoryException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body("Erro:" + e.getMessage());
        }catch (BusinessLogicException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED)
                    .body("Erro:"+e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
        return ResponseEntity.ok(studentSaved);
    }

    @GetMapping(path= "/{id}")
    @Operation(description = "End point para obter dados de um aluno somente")
    public ResponseEntity<Object> getById(
            @PathVariable("id") Long id){
        br.ueg.progweb1.aula01.model.Bike studentDB = br.ueg.progweb1.aula01.model.Bike.builder().id(0L).build(); // somente para não dar null
        try{
            studentDB = service.getById(id);
        }catch (DataException de){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro de dados ocorreu, detalhe:"+de.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
            return ResponseEntity.ok(studentDB);
    }

    @DeleteMapping(path= "/{id}")
    @Operation(description = "End point para apagar os dados de um aluno somente")
    public ResponseEntity<Object> remove(
            @PathVariable("id") Long id){
        br.ueg.progweb1.aula01.model.Bike studentDB = br.ueg.progweb1.aula01.model.Bike.builder().id(0L).build(); // somente para não dar null
        try{
            studentDB = service.deleteById(id);
        }catch (DataException de){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro de dados ocorreu, detalhe:"+de.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
        return ResponseEntity.ok(studentDB);
    }

    @PutMapping(path= "/{id}")
    @Operation(description = "End point para inclusão de aluno")
    public ResponseEntity<Object> update(
            @RequestBody br.ueg.progweb1.aula01.model.dtos.UpdateBikeDTO dto,
            @PathVariable("id") Long id){

        br.ueg.progweb1.aula01.model.Bike studentSaved =  null;
        try{
            br.ueg.progweb1.aula01.model.Bike data = mapper.toModel(dto);
            data.setId(id);
            studentSaved = service.update(data);
        }catch (MandatoryException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body("Erro:" + e.getMessage());
        }catch (BusinessLogicException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED)
                    .body("Erro:"+e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
        return ResponseEntity.ok(studentSaved);
    }

    @GetMapping(path = "/yesterday-registers")
    @Operation(description = "lista todos os estudantes matriculados antes de hoje")
    public ResponseEntity<List<br.ueg.progweb1.aula01.model.Bike>> listYesterdayRegisters(){
        return ResponseEntity.of(Optional.ofNullable(service.listYesterdayRegisters()));
    }

    @GetMapping
    @Operation(description = "lista todos os estudantes")
    public ResponseEntity<List<br.ueg.progweb1.aula01.model.Bike>> listAll(){

        List<br.ueg.progweb1.aula01.model.Bike> studentList = new ArrayList<>();
        try {
            studentList = service.listAll();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.of(
                Optional.ofNullable(studentList)
        );
    }
}
