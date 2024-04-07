package br.ueg.progweb1.crud_bike.controllers;

import br.ueg.progweb1.crud_bike.exceptions.BusinessLogicException;
import br.ueg.progweb1.crud_bike.exceptions.DataException;
import br.ueg.progweb1.crud_bike.exceptions.MandatoryException;
import br.ueg.progweb1.crud_bike.mapper.BikeMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "${api.version}/bike")
public class BikeController {

    @Autowired
    private br.ueg.progweb1.crud_bike.service.BikeService service;

    @Autowired
    private BikeMapper mapper;

    @PostMapping
    @Operation(description = "End point para inclusão de modelos de bike")
    public ResponseEntity<Object> create(@RequestBody br.ueg.progweb1.crud_bike.model.dtos.CreateBikeDTO dto){
        br.ueg.progweb1.crud_bike.model.Bike bikeSaved =  null;
        try{
            br.ueg.progweb1.crud_bike.model.Bike bikeModel = mapper.toModel(dto);
            bikeSaved = service.create(bikeModel);
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
        return ResponseEntity.ok(bikeSaved);
    }

    @GetMapping(path= "/{id}")
    @Operation(description = "End point para obter dados de um modelo de bike somente")
    public ResponseEntity<Object> getById(
            @PathVariable("id") Long id){
        br.ueg.progweb1.crud_bike.model.Bike bikeDB = br.ueg.progweb1.crud_bike.model.Bike.builder().id(0L).build(); // somente para não dar null
        try{
            bikeDB = service.getById(id);
        }catch (DataException de){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro de dados ocorreu, detalhe:"+de.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
            return ResponseEntity.ok(bikeDB);
    }

    @DeleteMapping(path= "/{id}")
    @Operation(description = "End point para apagar os dados de um modelo de bike somente")
    public ResponseEntity<Object> remove(
            @PathVariable("id") Long id){
        br.ueg.progweb1.crud_bike.model.Bike bikeDB = br.ueg.progweb1.crud_bike.model.Bike.builder().id(0L).build(); // somente para não dar null
        try{
            bikeDB = service.deleteById(id);
        }catch (DataException de){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro de dados ocorreu, detalhe:"+de.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
        return ResponseEntity.ok(bikeDB);
    }

    @PutMapping(path= "/{id}")
    @Operation(description = "End point para alteração de modelo de bike")
    public ResponseEntity<Object> update(
            @RequestBody br.ueg.progweb1.crud_bike.model.dtos.UpdateBikeDTO dto,
            @PathVariable("id") Long id){

        br.ueg.progweb1.crud_bike.model.Bike bikeSaved =  null;
        try{
            br.ueg.progweb1.crud_bike.model.Bike data = mapper.toModel(dto);
            data.setId(id);
            bikeSaved = service.update(data);
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
        return ResponseEntity.ok(bikeSaved);
    }

    @GetMapping(path = "/mtb-only")
    @Operation(description = "lista todos os modelos de bike que são mountain bike (somente)")
    public ResponseEntity<List<br.ueg.progweb1.crud_bike.model.Bike>> listOnlyMountainBikes(){
        return ResponseEntity.of(Optional.ofNullable(service.listOnlyMountainBikes()));
    }

    @GetMapping
    @Operation(description = "lista todos os modelos de bike")
    public ResponseEntity<List<br.ueg.progweb1.crud_bike.model.Bike>> listAll(){

        List<br.ueg.progweb1.crud_bike.model.Bike> bikeList = new ArrayList<>();
        try {
            bikeList = service.listAll();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.of(
                Optional.ofNullable(bikeList)
        );
    }

    @PatchMapping(path= "/{id}")
    @Operation(description = "End point para alterar a descrição do modelo de bike")
    public ResponseEntity<Object> patch(
            @RequestBody br.ueg.progweb1.crud_bike.model.dtos.PatchBikeDTO dto,
            @PathVariable("id") Long id){

        br.ueg.progweb1.crud_bike.model.Bike bikeSaved =  null;
        try{
            br.ueg.progweb1.crud_bike.model.Bike data = mapper.toModel(dto);
            data.setId(id);
            bikeSaved = service.patch(data);
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
        return ResponseEntity.ok(bikeSaved);
    }
}
