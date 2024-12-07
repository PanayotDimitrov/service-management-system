package uni.project.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.rest.api.entity.Car;
import uni.project.rest.api.model.CreateCarDTO;
import uni.project.rest.api.model.ResponseCarDTO;
import uni.project.rest.api.model.UpdateCarDTO;
import uni.project.rest.api.service.CarService;

import java.util.List;

@RestController
@CrossOrigin("http://Localhost:3000")
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping("/cars")
    @CrossOrigin("http://Localhost:3000/cars")
    public List<Car> getAllCars(@RequestParam String carMake,
                                           @RequestParam Long garageId,
                                           @RequestParam int fromYear,
                                           @RequestParam int toYear)
    {
        return carService.getCarsByFilters(carMake, garageId, fromYear, toYear);
    }

    @GetMapping("/cars/{id}")
    @CrossOrigin("http://Localhost:3000/cars/{id}")
    public Car getCarById(@PathVariable int id) {
        return carService.getCarById(id);
    }

    @PostMapping("/cars")
    @CrossOrigin("http://Localhost:3000/cars")
    public void createCar (@RequestBody CreateCarDTO createCarDTO) {
        carService.addCar(createCarDTO);
    }

    @PutMapping("/cars/{id}")
    @CrossOrigin("http://Localhost:3000/cars/{id}")
    public void updateCar(@PathVariable Long id, @RequestBody UpdateCarDTO updateCarDTO) {
        carService.updateCar(id,updateCarDTO);
    }

    @DeleteMapping("/cars/{id}")
    @CrossOrigin("http://Localhost:3000/cars/{id}")
    public void deleteCar(@PathVariable int id) {
        carService.deleteCarById(id);
    }
}
