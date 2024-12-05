package uni.project.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.project.rest.api.entity.Car;
import uni.project.rest.api.service.CarService;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }



    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

   @GetMapping("/cars/{id}")
    public Car getCarById(@PathVariable int id) {
        return carService.getCarById(id);
   }

   @PostMapping("/cars")
    public Car addCar(@RequestBody Car car) {
        carService.createCar(car);
        return car;
   }

   @PutMapping("/cars/{theId}")
    public Car updateCar(@PathVariable int theId, @RequestBody Car newCar) {
        return carService.updateCar(theId, newCar);
   }

   @DeleteMapping("/cars/{theId}")
    public void deleteCar(@PathVariable int theId) {
        carService.deleteCarById(theId);
   }
}
