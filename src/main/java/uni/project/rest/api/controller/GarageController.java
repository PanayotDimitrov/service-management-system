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
    public Garage createGarage(@RequestBody CreateGarageDTO createGarageDTO) {
        return garageService.createGarage(createGarageDTO);
    }

//    --------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/garages")
    @CrossOrigin("http://localhost:3000/garages")
    public List<Garage> getGarageByCity(@RequestParam(required = false) String city) {
        if (city == null || city.isEmpty()) {
            return garageService.getAllGarages();
        }else {
            return garageService.getAllGaragesByCity(city);
        }
    }

//    --------------------------------------------------------------------------------------------------------------------------------


    @GetMapping("/garages/{id}")
    @CrossOrigin("http://localhost:3000/garages/{id}")
    public ResponseGarageDTO getGarageDTOById(@PathVariable Long id) {
        return garageService.getGarageById(id);
    }

    // Get Garage Daily Availability Report
    @GetMapping("/garages/dailyAvailabilityReport")
    @CrossOrigin("http://localhost:3000/garages/dailyAvailabilityReport")
    public ResponseEntity<List<GarageDailyAvailabilityReportDTO>> getGarageDailyAvailabilityReport(
            @RequestParam Long garageId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return new ResponseEntity<>(garageService.getGarageDailyAvailabilityReport(garageId, startDate, endDate), HttpStatus.OK);
    }


    @PutMapping("/garages/{id}")
    @CrossOrigin("http://localhost:3000/garages/{id}")
//    public ResponseGarageDTO updateGarage(@PathVariable Long id, @RequestBody UpdateGarageDTO updateGarageDTO) {
//        return garageService.updateGarage(id, updateGarageDTO);
//    }
    public ResponseGarageDTO updateGarage(
            @PathVariable Long id,
            @RequestBody UpdateGarageDTO updateGarageDTO) {

        if(updateGarageDTO.getName() == null || updateGarageDTO.getName().isEmpty() ||
                updateGarageDTO.getLocation() == null || updateGarageDTO.getLocation().isEmpty() ||
                updateGarageDTO.getCity() == null || updateGarageDTO.getCity().isEmpty() ||
                updateGarageDTO.getCapacity() <=0)  {
            return null;
        }

        return garageService.updateGarage(id, updateGarageDTO);

    }



    @DeleteMapping("/garages/{id}")
    @CrossOrigin("http://localhost:3000/garages/{id}")
    public ResponseEntity<Void> deleteGarage(@PathVariable Long id) {
        garageService.deleteGarageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
