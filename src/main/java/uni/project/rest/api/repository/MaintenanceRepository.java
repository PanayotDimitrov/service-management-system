package uni.project.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uni.project.rest.api.entity.Maintenance;

@RepositoryRestResource(exported = false)
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
