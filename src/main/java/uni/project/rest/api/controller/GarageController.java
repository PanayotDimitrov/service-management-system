package uni.project.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.rest.api.entity.Garage;
import uni.project.rest.api.model.CreateGarageDTO;
import uni.project.rest.api.model.GarageDailyAvailabilityReportDTO;
import uni.project.rest.api.model.ResponseGarageDTO;
import uni.project.rest.api.model.UpdateGarageDTO;
import uni.project.rest.api.service.GarageService;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class GarageController {

    @Autowired
    private GarageService garageService;


    @PostMapping("/garages")
    @CrossOrigin("http://localhost:3000/garages")
    public void createGarage(@RequestBody CreateGarageDTO createGarageDTO) {
        garageService.addGarage(createGarageDTO);
    }

    @GetMapping("/garages")
    @CrossOrigin("http://localhost:3000/garages")
    public List<Garage> getGarageByCity(@RequestParam String city) {
        return garageService.getAllGaragesByCity(city);
    }

    @GetMapping("/garages/{id}")
    @CrossOrigin("http://localhost:3000/garages/{id}")
    public ResponseGarageDTO getGarageDTOById(@PathVariable Long id) {
        return garageService.getGarageById(id);
    }


    @PutMapping("/garages/{id}")
    @CrossOrigin("http://localhost:3000/garages/{id}")
    public ResponseEntity<ResponseGarageDTO> updateGarage(@PathVariable Long id, @RequestBody UpdateGarageDTO updateGarageDTO) {
        return new ResponseEntity<>(garageService.updateGarage(id, updateGarageDTO), HttpStatus.OK);
    }

    @DeleteMapping("/garages/{id}")
    @CrossOrigin("http://localhost:3000/garages/{id}")
    public ResponseEntity<Void> deleteGarage(@PathVariable Long id) {
        garageService.deleteGarageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Get Garage Daily Availability Report
    @GetMapping("/garages/dailyAvailabilityReport")
    @CrossOrigin("http://localhost:8088/garages/dailyAvailabilityReport")
    public ResponseEntity<List<GarageDailyAvailabilityReportDTO>> getGarageDailyAvailabilityReport(
            @RequestParam Long garageId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return new ResponseEntity<>(garageService.getGarageDailyAvailabilityReport(garageId, startDate, endDate), HttpStatus.OK);
    }
}
