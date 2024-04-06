package br.ueg.progweb1.aula01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository
        extends JpaRepository<br.ueg.progweb1.aula01.model.Bike, Long> {

    Optional<br.ueg.progweb1.aula01.model.Bike> findByRegisterNumber(String registerNumber);

    @Query("select s from Student s where s.createdDate < CURRENT_DATE")
    Optional<List<br.ueg.progweb1.aula01.model.Bike>> findYesterdayRegisters();
}