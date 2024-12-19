package uni.project.rest.api.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.project.rest.api.entity.Car;
import uni.project.rest.api.entity.Garage;
import uni.project.rest.api.entity.Maintenance;
import uni.project.rest.api.exception.ResourceNotFoundException404;
import uni.project.rest.api.model.*;
import uni.project.rest.api.repository.CarRepository;
import uni.project.rest.api.repository.GarageRepository;
import uni.project.rest.api.repository.MaintenanceRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MaintenanceService {
    private final EntityManager entityManager;

    private final MaintenanceRepository maintenanceRepository;

    private final CarRepository carRepository;

    private final GarageRepository garageRepository;



    public ResponseMaintenanceDTO getMaintenanceById(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException404("Maintenance not found"));
        return mapMaintanceToResponseDTO(maintenance);
    }

    @Transactional
    public ResponseMaintenanceDTO updateMaintenanceById(Long id, UpdateMaintenanceDTO updateMaintenanceDTO) {
        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException404("Maintenance not found"));
        maintenance.setCarId(updateMaintenanceDTO.getCarId());
        maintenance.setServiceType(updateMaintenanceDTO.getServiceType());
        maintenance.setScheduledDate(updateMaintenanceDTO.getScheduledDate());
        maintenance.setGarageId(updateMaintenanceDTO.getGarageId());
        return mapMaintanceToResponseDTO(maintenance);
    }

    @Transactional
    public void deleteMaintenanceById(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException404("Maintenance not found");
        }
        maintenanceRepository.deleteById(id);
    }

    public List<Maintenance> getAllMaintenance(Long carId, Long garageId, LocalDate startDate, LocalDate endDate) {
        List<Maintenance> maintenances = maintenanceRepository.findAllByFilters(carId, garageId, startDate, endDate);
        return maintenances;
    }

    @Transactional
    public ResponseMaintenanceDTO createMaintenance(CreateMaintenanceDTO createMaintenanceDTO) {
        // Validate if car exists
        if (!carRepository.existsById(createMaintenanceDTO.getCarId())) {
            throw new ResourceNotFoundException404("Car not found");
        }

        // Validate if garage exists
        if (!garageRepository.existsById(createMaintenanceDTO.getGarageId())) {
            throw new ResourceNotFoundException404("Garage not found");
        }

        Maintenance maintenance = new Maintenance();
        maintenance.setCarId(createMaintenanceDTO.getCarId());
        maintenance.setGarageId(createMaintenanceDTO.getGarageId());
        maintenance.setServiceType(createMaintenanceDTO.getServiceType());
        maintenance.setScheduledDate(createMaintenanceDTO.getScheduledDate());
        maintenanceRepository.save(maintenance);

       return mapMaintanceToResponseDTO(maintenance);
    }

    public List<Map<String, Object>> getMonthlyRequestsReport(Long garageId, LocalDate startMonth, LocalDate endMonth) {
        List<Object[]> rawResults = maintenanceRepository.findMonthlyRequestsReportRaw(garageId, startMonth, endMonth);

        Map<String, Map<String, Object>> monthMap = new LinkedHashMap<>();

        LocalDate currentMonth = startMonth.withDayOfMonth(1); // Ensure it's the first day of the month
        while (!currentMonth.isAfter(endMonth)) {
            int year = currentMonth.getYear();
            int monthValue = currentMonth.getMonthValue();
            String monthName = Month.of(monthValue).name();
            monthName = year + " " + monthName.substring(0, 1).toUpperCase() + monthName.substring(1);

            Map<String, Object> record = new HashMap<>();
            record.put("year", year);
            record.put("yearMonth", monthName);
            record.put("monthValue", monthValue);
            record.put("isLeap", java.time.Year.of(year).isLeap());
            record.put("requests", 0L); // Default to 0 requests

            monthMap.put(year + "-" + monthValue, record);

            currentMonth = currentMonth.plusMonths(1); // Move to the next month
        }

        for (Object[] result : rawResults) {
            int year = ((Number) result[0]).intValue();
            int monthValue = ((Number) result[1]).intValue();
            long requests = ((Number) result[2]).longValue();

            String key = year + "-" + monthValue;
            if (monthMap.containsKey(key)) {
                monthMap.get(key).put("requests", requests);
            }
        }

        return new ArrayList<>(monthMap.values());
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


}

