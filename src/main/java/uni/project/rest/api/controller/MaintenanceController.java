package uni.project.rest.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.rest.api.entity.Garage;
import uni.project.rest.api.entity.Maintenance;
import uni.project.rest.api.model.*;
import uni.project.rest.api.repository.GarageRepository;
import uni.project.rest.api.service.MaintenanceService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://Localhost:3000")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private GarageRepository garageRepository;

    @PostMapping("/maintenance")
    @CrossOrigin("http://Localhost:3000/maintenance")
    public ResponseMaintenanceDTO createMaintenance(@RequestBody CreateMaintenanceDTO dto) {
        return maintenanceService.createMaintenance(dto);
    }

    @GetMapping("maintenance/{id}")
    @CrossOrigin("http://Localhost:3000/maintenance/{id}")
    public ResponseEntity<ResponseMaintenanceDTO> getMaintenanceById(@PathVariable Long id) {
        try{
            ResponseMaintenanceDTO dto = maintenanceService.getMaintenanceById(id);
            if (dto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            else if (id == null || id <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(maintenanceService.getMaintenanceById(id));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();}
    }


    @PutMapping("maintenance/{id}")
    @CrossOrigin("http://localhost:8088/maintenance/{recordToUpdate.id}")
    public ResponseMaintenanceDTO updateMaintenanceById(
            @PathVariable Long id,
            @RequestBody UpdateMaintenanceDTO updateMaintenanceDTO) {
        return maintenanceService.updateMaintenanceById(id, updateMaintenanceDTO);
    }

    @DeleteMapping("maintenance/{id}")
    @CrossOrigin("http://Localhost:3000/maintenance/{id}")
    public void deleteMaintenanceById(@PathVariable Long id) {
        maintenanceService.deleteMaintenanceById(id);
    }

    @GetMapping("maintenance/monthlyRequestsReport")
    @CrossOrigin("http://Localhost:3000/maintenance/monthlyRequestsReport")
    public List<Map<String,Object>> getMonthlyRequestsReport(
            @RequestParam(required = false) Long garageId,
            @RequestParam String startMonth,
            @RequestParam String endMonth
    ) {
        LocalDate startDate = LocalDate.parse(startMonth + "-01");
        LocalDate endDate = LocalDate.parse(endMonth + "-01")
                .withDayOfMonth(YearMonth.from(LocalDate.parse(endMonth + "-01")).lengthOfMonth());
        return maintenanceService.getMonthlyRequestsReport(garageId, startDate, endDate);
    }


    @GetMapping("/maintenance")
    @CrossOrigin("http://localhost:3000/maintenance")
    public List<Maintenance> getAllMaintenance(
            @RequestParam(required = false) Long carId,
            @RequestParam(required = false) Long garageId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return maintenanceService.getAllMaintenance(carId, garageId, startDate, endDate);
    }
}
