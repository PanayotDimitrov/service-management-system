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
import java.util.List;
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


    public List<MonthlyRequestsReportDTO> getMonthlyRequestsReport(Long garageId, LocalDate startMonth, LocalDate endMonth) {
        return maintenanceRepository.findMonthlyRequestsReport(garageId, startMonth, endMonth);
    }



@Transactional
    public void createMaintenance(CreateMaintenanceDTO dto) {
        // Validate if car exists
        if (!carRepository.existsById(dto.getCarId())) {
            throw new EntityNotFoundException("Car not found with ID: " + dto.getCarId());
        }

        // Validate if garage exists
        if (!garageRepository.existsById(dto.getGarageId())) {
            throw new EntityNotFoundException("Garage not found with ID: " + dto.getGarageId());
        }

        // Map DTO to Entity
        Maintenance maintenance = new Maintenance();
        maintenance.setCarId(dto.getCarId());
        maintenance.setGarageId(dto.getGarageId());
        maintenance.setServiceType(dto.getServiceType());
        maintenance.setScheduledDate(dto.getScheduledDate());

        // Save entity
        maintenanceRepository.save(maintenance);
    }


    public ResponseMaintenanceDTO getMaintenanceById(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance not found with id: " + id));
        return mapToResponseDTO(maintenance);
    }

    @Transactional
    public void updateMaintenanceById(Long id, UpdateMaintenanceDTO updateMaintenanceDTO) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance not found with id: " + id));
        maintenance.setCarId(updateMaintenanceDTO.getCarId());
        maintenance.setServiceType(updateMaintenanceDTO.getServiceType());
        maintenance.setScheduledDate(updateMaintenanceDTO.getScheduledDate());
        maintenance.setGarageId(updateMaintenanceDTO.getGarageId());
        maintenanceRepository.save(maintenance);
    }

    public void deleteMaintenanceById(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new RuntimeException("Maintenance not found with id: " + id);
        }
        maintenanceRepository.deleteById(id);
    }

    public List<MonthlyRequestsReportDTO> monthlyRequestReport(Long garageId, LocalDate startMonth, LocalDate endMonth) {
        return maintenanceRepository.findMonthlyRequestsReport(garageId, startMonth, endMonth);
    }

    public List<ResponseMaintenanceDTO> getAllMaintenance(Long carId, Long garageId, LocalDate startDate, LocalDate endDate) {
        List<Maintenance> maintenances = maintenanceRepository.findAllByFilters(carId, garageId, startDate, endDate);
        return maintenances.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private ResponseMaintenanceDTO mapToResponseDTO(Maintenance maintenance) {
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
