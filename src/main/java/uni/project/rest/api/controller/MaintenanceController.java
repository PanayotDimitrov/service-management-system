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
@RequestMapping("/maintenance")
@CrossOrigin("http://Localhost:3000")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private GarageRepository garageRepository;

    @PostMapping
    @CrossOrigin("http://Localhost:3000/maintenance")
    public ResponseMaintenanceDTO createMaintenance(@RequestBody CreateMaintenanceDTO dto) {

        return maintenanceService.createMaintenance(dto);
    }

    @GetMapping("/{id}")
    @CrossOrigin("http://Localhost:3000/maintenance/{id}")
    public ResponseMaintenanceDTO getMaintenanceById(@PathVariable Long id) {
        return maintenanceService.getMaintenanceById(id);
    }

    @PutMapping("/{id}")
    @CrossOrigin("http://Localhost:3000/maintenance/{id}")
    public ResponseMaintenanceDTO updateMaintenanceById(
            @PathVariable Long id,
            @RequestBody UpdateMaintenanceDTO updateMaintenanceDTO) {
        return maintenanceService.updateMaintenanceById(id, updateMaintenanceDTO);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://Localhost:3000/maintenance/{id}")
    public void deleteMaintenanceById(@PathVariable Long id) {
        maintenanceService.deleteMaintenanceById(id);
    }

    @GetMapping("/monthlyRequestsReport")
    @CrossOrigin("http://Localhost:3000/maintenance/monthlyRequestsReport")
//    public List<MonthlyRequestsReportDTO> getMonthlyRequestsReport(
//            @RequestParam Long garageId,
//            @RequestParam String startMonth,
//            @RequestParam String endMonth) {
//        LocalDate startDate = LocalDate.parse(startMonth + "-01");
//        LocalDate endDate = LocalDate.parse(endMonth + "-01").withDayOfMonth(
//                YearMonth.from(LocalDate.parse(endMonth + "-01")).lengthOfMonth()
//        );
//        return maintenanceService.getMonthlyRequestsReport(garageId, startDate, endDate);
//    }
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


    @GetMapping
    public ResponseEntity<List<ResponseMaintenanceDTO>> getAllMaintenance(
            @RequestParam(required = false) Long carId,
            @RequestParam(required = false) Long garageId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(maintenanceService.getAllMaintenance(carId, garageId, startDate, endDate));
    }
}
