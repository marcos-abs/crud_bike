package br.ueg.progweb1.crud_bike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BikeRepository
        extends JpaRepository<br.ueg.progweb1.crud_bike.model.Bike, Long> {

    Optional<br.ueg.progweb1.crud_bike.model.Bike> findBySerialNumber(String serialNumber);

    @Query("select s from Bike s where s.isMTB = TRUE")
    Optional<List<br.ueg.progweb1.crud_bike.model.Bike>> findOnlyMountainBikes();
}
