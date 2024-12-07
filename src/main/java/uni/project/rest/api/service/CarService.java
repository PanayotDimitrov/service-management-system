package uni.project.rest.api.service;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.project.rest.api.entity.Car;
import uni.project.rest.api.entity.Garage;
import uni.project.rest.api.model.CreateCarDTO;
import uni.project.rest.api.model.ResponseCarDTO;
import uni.project.rest.api.model.UpdateCarDTO;
import uni.project.rest.api.repository.CarRepository;
import uni.project.rest.api.repository.GarageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GarageRepository garageRepository;


   public List<Car> getCarsByFilters(String make, Long garageId, int fromYear, int toYear) {
       return carRepository.findCarsByMakeAndGarageAndYearRange(make, garageId, fromYear, toYear);
   }

    public Car getCarById(int id) {
        return entityManager.find(Car.class, id);
    }

    public ResponseCarDTO updateCar(Long id, UpdateCarDTO updateCarDTO) {
        Car dbCar = carRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Car not found"));

        if (updateCarDTO.getMake() != null) {
            dbCar.setMake(updateCarDTO.getMake());
        }
        if (updateCarDTO.getModel() != null) {
            dbCar.setModel(updateCarDTO.getModel());
        }
        if (updateCarDTO.getProductionYear()!= 0) {
            dbCar.setProductionYear(updateCarDTO.getProductionYear());
        }
        if (updateCarDTO.getLicensePlate() != null) {
            dbCar.setLicensePlate(updateCarDTO.getLicensePlate());
        }

        if(updateCarDTO.getGarages() != null && !updateCarDTO.getGarages().isEmpty()) {
            List<Garage> garages = garageRepository.findAllById(updateCarDTO.getGarages());
            dbCar.setGarages(garages);
        }

        Car updatedCar = carRepository.save(dbCar);
        return mapToResponseDTO(updatedCar);
    }

    public ResponseCarDTO addCar(CreateCarDTO createCarDTO) {
        Car car = new Car();
        car.setMake(createCarDTO.getMake());
        car.setModel(createCarDTO.getModel());
        car.setProductionYear(createCarDTO.getProductionYear());
        car.setLicensePlate(createCarDTO.getLicensePlate());

        List<Garage> garages = garageRepository.findAllById(createCarDTO.getGarageIds());
        car.setGarages(garages);

        Car savedCar = carRepository.save(car);

        return mapToResponseDTO(savedCar);
    }

    public void deleteCarById(int id) {
        carRepository.delete(getCarById(id));
    }









    private ResponseCarDTO mapToResponseDTO(Car car) {

        ResponseCarDTO responseCarDTO = new ResponseCarDTO();

        responseCarDTO.setId(car.getId());
        responseCarDTO.setMake(car.getMake());
        responseCarDTO.setModel(car.getModel());
        responseCarDTO.setProductionYear(car.getProductionYear());
        responseCarDTO.setLicensePlate(car.getLicensePlate());

        List<Long> garageIds = car.getGarages()
                .stream().map(Garage::getId).collect(Collectors.toList());

        responseCarDTO.setGarageIds(garageIds);
        return responseCarDTO;
    }


}
