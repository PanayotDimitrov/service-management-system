package uni.project.rest.api.repository;

import org.springframework.data.repository.CrudRepository;
import uni.project.rest.api.entity.Garage;

public interface GarageRepository extends CrudRepository<Garage, Long> {
}
