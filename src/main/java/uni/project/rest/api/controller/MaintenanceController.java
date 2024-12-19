package uni.project.rest.api.controller;


import com.sun.tools.javac.Main;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uni.project.rest.api.entity.Garage;
import uni.project.rest.api.entity.Maintenance;
import uni.project.rest.api.exception.BadRequestException;
import uni.project.rest.api.exception.ResourceNotFoundException404;
import uni.project.rest.api.model.*;
import uni.project.rest.api.service.GarageService;
import uni.project.rest.api.service.MaintenanceService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;
    private final GarageService garageService;

    @Autowired
    public MaintenanceController(MaintenanceService maintenanceService, GarageService garageService) {
        this.maintenanceService = maintenanceService;
        this.garageService = garageService;
    }


    @GetMapping("maintenance/{id}")
    public ResponseEntity<ResponseMaintenanceDTO> getMaintenanceById(@PathVariable Long id) {
        return new ResponseEntity<>(maintenanceService.getMaintenanceById(id), HttpStatus.OK);
    }


    @PutMapping("maintenance/{id}")
    public ResponseEntity<ResponseMaintenanceDTO> updateMaintenanceById(
            @PathVariable Long id,
            @RequestBody UpdateMaintenanceDTO updateMaintenanceDTO) {
        return new ResponseEntity<>(maintenanceService.updateMaintenanceById(id, updateMaintenanceDTO), HttpStatus.OK);
    }



    @DeleteMapping("maintenance/{id}")
    public void deleteMaintenanceById(@PathVariable Long id) {
        maintenanceService.deleteMaintenanceById(id);
    }

    @GetMapping("/maintenance")
    public List<Maintenance> getAllMaintenance(
            @RequestParam(required = false) Long carId,
            @RequestParam(required = false) Long garageId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return maintenanceService.getAllMaintenance(carId, garageId, startDate, endDate);
    }

    @PostMapping("/maintenance")
    public ResponseMaintenanceDTO createMaintenance(@RequestBody CreateMaintenanceDTO dto) {
        return maintenanceService.createMaintenance(dto);
    }



    @GetMapping("maintenance/monthlyRequestsReport")
    public List<Map<String,Object>> getMonthlyRequestsReport(
            @RequestParam(required = false) Long garageId,
            @RequestParam String startMonth,
            @RequestParam String endMonth
    ) {
        try {
            LocalDate startDate = LocalDate.parse(startMonth + "-01");
            LocalDate endDate = LocalDate.parse(endMonth + "-01")
                    .withDayOfMonth(YearMonth.from(LocalDate.parse(endMonth + "-01")).lengthOfMonth());
            if (startDate.isAfter(endDate)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date cannot be after end date");
            }

            if (garageId != null && !garageService.isGarageExists(garageId)) {
                throw new ResourceNotFoundException404("Garage with id " + garageId + " not found");
            }

            return maintenanceService.getMonthlyRequestsReport(garageId, startDate, endDate);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }


}
