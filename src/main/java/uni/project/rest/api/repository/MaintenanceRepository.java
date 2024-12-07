package uni.project.rest.api.repository;

import org.springframework.data.repository.CrudRepository;
import uni.project.rest.api.entity.Maintenance;

public interface MaintenanceRepository extends CrudRepository<Maintenance, Long> {
}
