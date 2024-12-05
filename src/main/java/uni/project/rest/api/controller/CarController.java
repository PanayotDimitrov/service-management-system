package uni.project.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/getcars")
    public List<Car> getCars() {
        return carService.getAllCars();
    }

    @PostMapping("/car")
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

}
