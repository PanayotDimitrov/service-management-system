package uni.project.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uni.project.rest.api.entity.Garage;

@RepositoryRestResource(exported = false)
public interface GarageRepository extends JpaRepository<Garage, Long> {
}
