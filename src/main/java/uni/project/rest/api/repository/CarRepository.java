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
            "WHERE c.make = :make " +
            "AND g.id = :garageId " +
            "AND c.productionYear BETWEEN :fromYear AND :toYear")
    List<Car> findCarsByMakeAndGarageAndYearRange(String make, Long garageId, int fromYear, int toYear);

}
