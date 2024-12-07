package uni.project.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uni.project.rest.api.entity.Garage;
import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface GarageRepository extends JpaRepository<Garage, Long> {

    @Query("SELECT m.scheduledDate, COUNT(m.id) " +
            "FROM Maintenance m " +
            "WHERE m.garageId = :garageId AND m.scheduledDate BETWEEN :startDate AND :endDate " +
            "GROUP BY m.scheduledDate")
    List<Object[]> findGarageDailyAvailabilityReport(Long garageId, LocalDate startDate, LocalDate endDate);


}
