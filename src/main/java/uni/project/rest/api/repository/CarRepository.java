package uni.project.rest.api.repository;

import org.springframework.data.repository.CrudRepository;
import uni.project.rest.api.entity.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
}
