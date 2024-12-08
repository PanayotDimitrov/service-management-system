package uni.project.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uni.project.rest.api.entity.Car;
import uni.project.rest.api.model.ResponseCarDTO;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface CarRepository extends JpaRepository<Car, Long> {


    @Query("SELECT c FROM Car c " +
            "JOIN c.garages g " +
            "WHERE (:make IS NULL OR c.make = :make) " +
            "AND (:garageId IS NULL OR g.id = :garageId) " +
            "AND (:fromYear IS NULL OR :toYear IS NULL OR c.productionYear BETWEEN :fromYear AND :toYear)")
    List<Car> findCarsByMakeAndGarageAndYearRange(
            @Param("make") String make,
            @Param("garageId") Long garageId,
            @Param("fromYear") Integer fromYear,
            @Param("toYear") Integer toYear);

}
