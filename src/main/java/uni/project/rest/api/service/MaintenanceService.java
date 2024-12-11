package uni.project.rest.api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.project.rest.api.entity.Car;
import uni.project.rest.api.entity.Garage;
import uni.project.rest.api.entity.Maintenance;
import uni.project.rest.api.model.*;
import uni.project.rest.api.repository.CarRepository;
import uni.project.rest.api.repository.GarageRepository;
import uni.project.rest.api.repository.MaintenanceRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MaintenanceService {

    @Autowired
    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    private final CarRepository carRepository;

    @Autowired
    private final GarageRepository garageRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository, CarRepository carRepository, GarageRepository garageRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.carRepository = carRepository;
        this.garageRepository = garageRepository;
    }


@Transactional
    public ResponseMaintenanceDTO createMaintenance(CreateMaintenanceDTO createMaintenanceDTO) {
        // Validate if car exists
        if (!carRepository.existsById(createMaintenanceDTO.getCarId())) {
            throw new EntityNotFoundException("Car not found with ID: " + createMaintenanceDTO.getCarId());
        }

        // Validate if garage exists
        if (!garageRepository.existsById(createMaintenanceDTO.getGarageId())) {
            throw new EntityNotFoundException("Garage not found with ID: " + createMaintenanceDTO.getGarageId());
        }

        Maintenance maintenance = new Maintenance();
        maintenance.setCarId(createMaintenanceDTO.getCarId());
        maintenance.setGarageId(createMaintenanceDTO.getGarageId());
        maintenance.setServiceType(createMaintenanceDTO.getServiceType());
        maintenance.setScheduledDate(createMaintenanceDTO.getScheduledDate());
        maintenanceRepository.save(maintenance);

       return mapMaintanceToResponseDTO(maintenance);
    }


    public ResponseMaintenanceDTO getMaintenanceById(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance not found with id: " + id));
        return mapMaintanceToResponseDTO(maintenance);
    }

    @Transactional
    public ResponseMaintenanceDTO updateMaintenanceById(Long id, UpdateMaintenanceDTO updateMaintenanceDTO) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance not found with id: " + id));

        maintenance.setCarId(updateMaintenanceDTO.getCarId());
        maintenance.setServiceType(updateMaintenanceDTO.getServiceType());
        maintenance.setScheduledDate(updateMaintenanceDTO.getScheduledDate());
        maintenance.setGarageId(updateMaintenanceDTO.getGarageId());
        return mapMaintanceToResponseDTO(maintenance);
    }

    @Transactional
    public void deleteMaintenanceById(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new RuntimeException("Maintenance not found with id: " + id);
        }
        maintenanceRepository.deleteById(id);
    }

    public List<Maintenance> getAllMaintenance(Long carId, Long garageId, LocalDate startDate, LocalDate endDate) {
        List<Maintenance> maintenances = maintenanceRepository.findAllByFilters(carId, garageId, startDate, endDate);
//        return maintenances.stream()
//                .map(this::mapMaintanceToResponseDTO)
//                .collect(Collectors.toList());
        return maintenances;
    }

    private ResponseMaintenanceDTO mapMaintanceToResponseDTO(Maintenance maintenance) {
        ResponseMaintenanceDTO dto = new ResponseMaintenanceDTO();
        dto.setId(maintenance.getId());
        dto.setCarId(maintenance.getCarId());
        dto.setServiceType(maintenance.getServiceType());
        dto.setScheduledDate(maintenance.getScheduledDate());
        dto.setGarageId(maintenance.getGarageId());

        String carName = carRepository.findById(maintenance.getCarId())
                        .map(Car::getMake)
                                .orElse("Unknown car");
        dto.setCarName(carName);

        String garageName = garageRepository.findById(maintenance.getGarageId())
                .map(Garage::getName)
                .orElse("Unknown Garage");
        dto.setGarageName(garageName);

        return dto;
    }


    public List<Map<String,Object>> getMonthlyRequestsReport(Long garageId,LocalDate startMonth, LocalDate endMonth) {
    List<Object[]> rawResults = maintenanceRepository.findMonthlyRequestsReportRaw(garageId, startMonth, endMonth);

    return rawResults.stream().map(result -> {
        int year = ((Number) result[0]).intValue();
        int monthValue = ((Number) result[1]).intValue();
        long requests = ((Number) result[2]).longValue();

        String monthName = Month.of(monthValue).name();
        monthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1).toLowerCase();

        Map<String, Object> record = new HashMap<>();
        record.put("year", year);
        record.put("yearMonth", monthName);
        record.put("monthValue", monthValue);
        record.put("isLeap", java.time.Year.of(year).isLeap());
        record.put("requests", requests);

        return record;
    }).collect(Collectors.toList());


    }

}
