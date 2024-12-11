package uni.project.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uni.project.rest.api.entity.Car;
import uni.project.rest.api.model.CreateCarDTO;
import uni.project.rest.api.model.ResponseCarDTO;
import uni.project.rest.api.model.ResponseGarageDTO;
import uni.project.rest.api.model.UpdateCarDTO;
import uni.project.rest.api.service.CarService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("http://Localhost:3000")
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping("/cars")
    @CrossOrigin("http://localhost:3000/cars")
    public List<Car> getAllCars(@RequestParam(required = false) String carMake,
                                @RequestParam(required = false) Long garageId,
                                @RequestParam(required = false) Integer fromYear,
                                @RequestParam(required = false) Integer toYear)
    {
        boolean isAnyFilterProvided = (carMake != null && !carMake.isEmpty()) ||
                (garageId != null) ||
                (fromYear != null && fromYear > 0) ||
                (toYear != null && toYear > 1);

        if (isAnyFilterProvided) {
            return carService.getCarsByFilters(carMake, garageId, fromYear, toYear);
        } else {
            return carService.getAllCars();
        }
    }

    @GetMapping("/cars/{id}")
    @CrossOrigin("http://localhost:3000/cars/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }


    @PostMapping("/cars")
    @CrossOrigin("http://Localhost:3000/cars")
    public ResponseCarDTO createCar (@RequestBody CreateCarDTO createCarDTO) {
        return carService.addCar(createCarDTO);
    }

    @PutMapping("/cars/{id}")
    @CrossOrigin("http://Localhost:3000/cars/{id}")
    public ResponseCarDTO updateCar(@PathVariable Long id, @RequestBody UpdateCarDTO updateCarDTO) {
        return carService.updateCar(id,updateCarDTO);
    }

    @DeleteMapping("/cars/{id}")
    @CrossOrigin("http://Localhost:3000/cars/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
    }
}
