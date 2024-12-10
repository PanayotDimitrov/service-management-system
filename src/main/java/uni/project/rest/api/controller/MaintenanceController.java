package uni.project.rest.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private GarageRepository garageRepository;

    @PostMapping
    public ResponseMaintenanceDTO createMaintenance(@RequestBody CreateMaintenanceDTO dto) {

        return maintenanceService.createMaintenance(dto);
//        Maintenance createdMaintenance = maintenanceService.createMaintenance(dto);

//        // Map to Response DTO
//        ResponseMaintenanceDTO response = new ResponseMaintenanceDTO();
//        response.setId(createdMaintenance.getId());
//        response.setCarId(createdMaintenance.getCarId());
//        response.setGarageId(createdMaintenance.getGarageId());
//        response.setServiceType(createdMaintenance.getServiceType());
//        response.setScheduledDate(createdMaintenance.getScheduledDate());
//
//        String carName = garageRepository.findById(createdMaintenance.getCarId())
//                        .map(Garage::getName)
//                                .orElse("Unknown car");
//
//        String garageName = garageRepository.findById(createdMaintenance.getGarageId())
//                        .map(Garage::getName)
//                .orElse("Unknown garage");
//
//        response.setCarName(carName);
//        response.setGarageName(garageName);
//
//        maintenanceService.createMaintenance(dto);
//        return null;
    }

    @GetMapping("/{id}")
    public ResponseMaintenanceDTO getMaintenanceById(@PathVariable Long id) {
        return maintenanceService.getMaintenanceById(id);
    }

    @PutMapping("/{id}")
    public void updateMaintenanceById(
            @PathVariable Long id,
            @RequestBody UpdateMaintenanceDTO updateMaintenanceDTO) {
        maintenanceService.updateMaintenanceById(id, updateMaintenanceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMaintenanceById(@PathVariable Long id) {
        maintenanceService.deleteMaintenanceById(id);
    }

    @GetMapping("/monthlyRequestsReport")
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
