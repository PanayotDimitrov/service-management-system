package uni.project.rest.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.rest.api.entity.Maintenance;
import uni.project.rest.api.model.*;
import uni.project.rest.api.repository.GarageRepository;
import uni.project.rest.api.service.MaintenanceService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;
    private final GarageRepository garageRepository;

    @PostMapping
    public ResponseMaintenanceDTO createMaintenance(@RequestBody @Valid CreateMaintenanceDTO dto) {
        Maintenance createdMaintenance = maintenanceService.createMaintenance(dto);

        // Map to Response DTO
        ResponseMaintenanceDTO response = new ResponseMaintenanceDTO();
        response.setId(createdMaintenance.getId());
        response.setCarId(createdMaintenance.getCarId());
        response.setGarageId(createdMaintenance.getGarageId());
        response.setServiceType(createdMaintenance.getServiceType());
        response.setScheduledDate(createdMaintenance.getScheduledDate());
        // Assuming carName and garageName need to be fetched from repositories
        response.setCarName(garageRepository.findById(createdMaintenance.getCarId()).orElseThrow().getName());
        response.setGarageName(garageRepository.findById(createdMaintenance.getGarageId()).orElseThrow().getName());
        maintenanceService.createMaintenance(dto);
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMaintenanceDTO> getMaintenanceById(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.getMaintenanceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMaintenanceById(
            @PathVariable Long id,
            @RequestBody UpdateMaintenanceDTO updateMaintenanceDTO) {
        maintenanceService.updateMaintenanceById(id, updateMaintenanceDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenanceById(@PathVariable Long id) {
        maintenanceService.deleteMaintenanceById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/maintenance/monthlyRequestsReport")
    public List<MonthlyRequestsReportDTO> getMonthlyRequestsReport(
            @RequestParam(required = false) Long garageId,
            @RequestParam String startMonth,
            @RequestParam String endMonth) {
        LocalDate startDate = LocalDate.parse(startMonth + "-01");
        LocalDate endDate = LocalDate.parse(endMonth + "-01").withDayOfMonth(
                YearMonth.from(LocalDate.parse(endMonth + "-01")).lengthOfMonth()
        );
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
