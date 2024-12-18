package uni.project.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.rest.api.entity.Garage;
import uni.project.rest.api.exception.BadRequestException;
import uni.project.rest.api.exception.ResourceNotFoundException404;
import uni.project.rest.api.model.CreateGarageDTO;
import uni.project.rest.api.model.GarageDailyAvailabilityReportDTO;
import uni.project.rest.api.model.ResponseGarageDTO;
import uni.project.rest.api.model.UpdateGarageDTO;
import uni.project.rest.api.service.GarageService;


import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
public class GarageController {


    private final GarageService garageService;

    @Autowired
    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @PostMapping("/garages")
    public Garage createGarage(@RequestBody CreateGarageDTO createGarageDTO) {
        return garageService.createGarage(createGarageDTO);
    }

//    --------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/garages")
    public List<Garage> getGarageByCity(@RequestParam(required = false) String city) throws BadRequestException {
        boolean flag = true;
        for (int i = 0; i < city.length(); i++) {
            if (i==0 && city.charAt(i) == '_')
                continue;
            if ( !Character.isDigit(city.charAt(i)))
                flag = false;
        }

        if (city == null || city.isEmpty()) {
                return garageService.getAllGarages();
        }
        else {
            if (flag){
                throw new BadRequestException("City must be a string");
            }else {
                return garageService.getAllGaragesByCity(city);
            }
        }
    }

//    --------------------------------------------------------------------------------------------------------------------------------


    @GetMapping("/garages/{id}")
    public ResponseGarageDTO getGarageDTOById(@PathVariable Long id) {
        return garageService.getGarageById(id);
    }

    @GetMapping("/garages/dailyAvailabilityReport")
    public ResponseEntity<List<GarageDailyAvailabilityReportDTO>> getGarageDailyAvailabilityReport(
            @RequestParam Long garageId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return new ResponseEntity<>(garageService.getGarageDailyAvailabilityReport(garageId, startDate, endDate), HttpStatus.OK);
    }


    @PutMapping("/garages/{id}")
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
    public void deleteGarage(@PathVariable Long id) {
        garageService.deleteGarageById(id);
    }

}
