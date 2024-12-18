package uni.project.rest.api.service;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.project.rest.api.entity.Car;
import uni.project.rest.api.entity.Garage;
import uni.project.rest.api.exception.ResourceNotFoundException404;
import uni.project.rest.api.model.CreateCarDTO;
import uni.project.rest.api.model.ResponseCarDTO;
import uni.project.rest.api.model.ResponseGarageDTO;
import uni.project.rest.api.model.UpdateCarDTO;
import uni.project.rest.api.repository.CarRepository;
import uni.project.rest.api.repository.GarageRepository;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarService {

    private final EntityManager entityManager;

    private final CarRepository carRepository;

    private final GarageRepository garageRepository;


    public List<Car> getCarsByFilters(String make,
                                      Long garageId,
                                      Integer fromYear,
                                      Integer toYear) {
        return carRepository.findCarsByMakeAndGarageAndYearRange(make, garageId, fromYear, toYear);
    }

   public List<Car> getAllCars(){
       return carRepository.findAll();
   }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException404("Car not found"));
    }

    @Transactional
    public void deleteCarById(Long id) {
        carRepository.delete(getCarById(id));
    }

    @Transactional
    public ResponseCarDTO updateCar(Long id,UpdateCarDTO updateCarDTO) {

        Car car = entityManager.find(Car.class, id);

        car.setMake(updateCarDTO.getMake());
        car.setModel(updateCarDTO.getModel());
        car.setProductionYear(updateCarDTO.getProductionYear());
        car.setLicensePlate(updateCarDTO.getLicensePlate());

        List<Garage> garages = garageRepository.findAllById(updateCarDTO.getGarageIds());

        car.setGarages(garages);

        entityManager.merge(car);

        return mapToResponseDTO(car);
    }

    @Transactional
    public ResponseCarDTO addCar(CreateCarDTO createCarDTO) {

        Car car = new Car();

        car.setMake(createCarDTO.getMake());
        car.setModel(createCarDTO.getModel());
        car.setProductionYear(createCarDTO.getProductionYear());
        car.setLicensePlate(createCarDTO.getLicensePlate());

        List<Garage> garages = garageRepository.findAllById(createCarDTO.getGarageIds());

        car.setGarages(garages);

        entityManager.merge(car);

        return mapToResponseDTO(car);
    }




    private ResponseCarDTO mapToResponseDTO(Car car) {

        ResponseCarDTO responseCarDTO = new ResponseCarDTO();

        responseCarDTO.setId(car.getId());
        responseCarDTO.setMake(car.getMake());
        responseCarDTO.setModel(car.getModel());
        responseCarDTO.setProductionYear(car.getProductionYear());
        responseCarDTO.setLicensePlate(car.getLicensePlate());

        List<ResponseGarageDTO> garages =car.getGarages()
                .stream()
                .map(garage -> new ResponseGarageDTO(
                        garage.getId(),
                        garage.getName(),
                        garage.getLocation(),
                        garage.getCity(),
                        garage.getCapacity()
                )).toList();
        responseCarDTO.setGarageIds(garages);

        return responseCarDTO;
    }


}
