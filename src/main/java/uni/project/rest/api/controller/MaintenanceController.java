package uni.project.rest.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.rest.api.entity.Maintenance;
import uni.project.rest.api.model.*;
import uni.project.rest.api.service.MaintenanceService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class MaintenanceController {


    private final MaintenanceService maintenanceService;


    @Autowired
    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping("/maintenance")
    public ResponseMaintenanceDTO createMaintenance(@RequestBody CreateMaintenanceDTO dto) {
        return maintenanceService.createMaintenance(dto);
    }

    @GetMapping("maintenance/{id}")
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
    public ResponseMaintenanceDTO updateMaintenanceById(
            @PathVariable Long id,
            @RequestBody UpdateMaintenanceDTO updateMaintenanceDTO) {
        return maintenanceService.updateMaintenanceById(id, updateMaintenanceDTO);
    }

    @DeleteMapping("maintenance/{id}")
    public void deleteMaintenanceById(@PathVariable Long id) {
        maintenanceService.deleteMaintenanceById(id);
    }

    @GetMapping("maintenance/monthlyRequestsReport")
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
    public List<Maintenance> getAllMaintenance(
            @RequestParam(required = false) Long carId,
            @RequestParam(required = false) Long garageId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return maintenanceService.getAllMaintenance(carId, garageId, startDate, endDate);
    }
}
