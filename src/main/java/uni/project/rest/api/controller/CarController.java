package uni.project.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.project.rest.api.entity.Car;
import uni.project.rest.api.service.interfaces.CarService;

import java.util.List;

@RestController
@CrossOrigin("http://Localhost:3000")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/cars")
    @CrossOrigin("http://localhost:3000/cars")
    public List<Car> getAllCars(@RequestParam String carMake,
                                @RequestParam long garageId,
                                @RequestParam int fromYear,
                                @RequestParam int toYear)
    {

        return carService.getAllCars(carMake, garageId, fromYear, toYear);

    }


   @GetMapping("/cars/{id}")
   @CrossOrigin("http://localhost:3000/cars/{id}")
   public Car getCarById(@PathVariable int id) {
        return carService.getCarById(id);
   }

   @PostMapping("/cars")
   @CrossOrigin("http://Localhost:3000/cars")
    public Car addCar(@RequestBody Car car) {
        carService.createCar(car);
        return car;
   }

   @PutMapping("/cars/{id}")
   @CrossOrigin("http://localhost:3000/cars/{id}")
   public Car updateCar(@PathVariable int id, @RequestBody Car newCar) {
        return carService.updateCar(id, newCar);
   }

   @DeleteMapping("/cars/{id}")
   @CrossOrigin("http://localhost:3000/cars/{id}")
    public void deleteCar(@PathVariable int id) {
        carService.deleteCarById(id);
   }
}
