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

//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Resource found", content = {@Content(mediaType = "application/json",
//            schema = @Schema(implementation = ResponseMaintenanceDTO.class))}),
//            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content),
//            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
//    })
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
        LocalDate startDate = LocalDate.parse(startMonth + "-01");
        LocalDate endDate = LocalDate.parse(endMonth + "-01")
                .withDayOfMonth(YearMonth.from(LocalDate.parse(endMonth + "-01")).lengthOfMonth());
        return maintenanceService.getMonthlyRequestsReport(garageId, startDate, endDate);
    }


}
