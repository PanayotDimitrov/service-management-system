package uni.project.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uni.project.rest.api.entity.Maintenance;
import uni.project.rest.api.model.MonthlyRequestsReportDTO;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    @Query("SELECT m FROM Maintenance m WHERE (:carId IS NULL OR m.carId = :carId) " +
            "AND (:garageId IS NULL OR m.garageId = :garageId) " +
            "AND (:startDate IS NULL OR m.scheduledDate >= :startDate) " +
            "AND (:endDate IS NULL OR m.scheduledDate <= :endDate)")
    List<Maintenance> findAllByFilters(
            @Param("carId") Long carId,
            @Param("garageId") Long garageId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
    @Query("SELECT EXTRACT(YEAR FROM m.scheduledDate) AS year, " +
            "EXTRACT(MONTH FROM m.scheduledDate) AS month, " +
            "COUNT(m) AS requests " +
            "FROM Maintenance m " +
            "WHERE (:garageId IS NULL OR m.garageId = :garageId) " +
            "AND m.scheduledDate BETWEEN :startMonth AND :endMonth " +
            "GROUP BY EXTRACT(YEAR FROM m.scheduledDate), EXTRACT(MONTH FROM m.scheduledDate)")
    List<Object[]> findMonthlyRequestsReportRaw(@Param("garageId") Long garageId,
                                                @Param("startMonth") LocalDate startMonth,
                                                @Param("endMonth") LocalDate endMonth);



}
