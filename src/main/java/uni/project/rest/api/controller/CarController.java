package uni.project.rest.api.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.rest.api.entity.Car;
import uni.project.rest.api.exception.ResourceNotFoundException404;
import uni.project.rest.api.model.CreateCarDTO;
import uni.project.rest.api.model.ResponseCarDTO;
import uni.project.rest.api.model.UpdateCarDTO;
import uni.project.rest.api.service.CarService;

import java.util.List;
import java.util.Optional;


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

//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Resource found" , content = {@Content(mediaType = "application/json",
//            schema = @Schema(implementation = ResponseCarDTO.class))}),
//            @ApiResponse(responseCode = "404", description = "Car not found", content = @Content),
//            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
//    })
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(required = false) Long id) {
        return new ResponseEntity<>(carService.getCarById(id), HttpStatus.OK);
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
