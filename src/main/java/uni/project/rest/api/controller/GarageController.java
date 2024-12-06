package uni.project.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.project.rest.api.entity.Garage;
import uni.project.rest.api.service.interfaces.GarageService;

import java.util.List;

@RestController
@CrossOrigin("http://Localhost:3000")
public class GarageController {

    @Autowired
    private GarageService garageService;

    @Autowired
    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }


    @PostMapping("/garages")
    @CrossOrigin("http://Localhost:3000/garages")
    public Garage createGarage(@RequestBody Garage garage) {
        return garageService.createGarage(garage);
    }

    @GetMapping("/garages/{theId}")
    public Garage getGarage(@PathVariable int theId) {
        return garageService.getGarageById(theId);
    }

    @GetMapping("/garages")
    @CrossOrigin("http://localhost:3000/garages")
    public List<Garage> getAllGaragesByCity(@RequestParam String city) {
           return garageService.getAllGaragesByCity(city);
    }

    @DeleteMapping("/garages/{theId}")
    @CrossOrigin("http://Localhost:3000/garages/{theId}")
    public void deleteGarage(@PathVariable int theId) {
        garageService.deleteGarageById(theId);
    }

    @PutMapping("/garages/{theId}")
    @CrossOrigin("http://localhost:3000/garages/{theId}")
    public Garage updateGarage(@PathVariable int theId, @RequestBody Garage garage) {
        return garageService.updateGarageById(garage, theId);
    }

//    @GetMapping("/garages")
//    @CrossOrigin("http://localhost:3000/garages")
//    public List<Garage> getAllGarages() {
//        return garageService.getAllGarages();
//    }

}
