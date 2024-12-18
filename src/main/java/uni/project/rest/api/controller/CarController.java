package uni.project.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.rest.api.entity.Car;
import uni.project.rest.api.model.CreateCarDTO;
import uni.project.rest.api.model.ResponseCarDTO;
import uni.project.rest.api.model.UpdateCarDTO;
import uni.project.rest.api.service.CarService;

import java.util.List;


@RestController
@CrossOrigin("*")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
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
    public ResponseEntity<Car> getCarById(@PathVariable(required = false) Long id) {
        Car car = carService.getCarById(id);
        if (id == null || id <=0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);

    }



    @PostMapping("/cars")
    public ResponseCarDTO createCar (@RequestBody CreateCarDTO createCarDTO) {
        return carService.addCar(createCarDTO);
    }

    @PutMapping("/cars/{id}")
    public ResponseCarDTO updateCar(@PathVariable Long id, @RequestBody UpdateCarDTO updateCarDTO) {
        return carService.updateCar(id,updateCarDTO);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);
    }
}
