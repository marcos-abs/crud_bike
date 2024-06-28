package br.ueg.progweb1.crud_bike.repository;

import br.ueg.progweb1.crud_bike.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BikeRepository
        extends JpaRepository<Bike, Long> {

    Optional<Bike> findByPartNumber(String partNumber);

    @Query("select b from Bike b where upper(b.description) like upper(concat('%',:description,'%'))")
    List<Bike> findByDescriptionContaining(@Param("description") String description);

    @Query("select b from Bike b where b.isMTB = TRUE")
    Optional<List<Bike>> findOnlyMountainBikes();
}
