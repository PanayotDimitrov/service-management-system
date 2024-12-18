package uni.project.rest.api.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.project.rest.api.entity.Garage;
import uni.project.rest.api.exception.ResourceNotFoundException404;
import uni.project.rest.api.model.CreateGarageDTO;
import uni.project.rest.api.model.GarageDailyAvailabilityReportDTO;
import uni.project.rest.api.model.ResponseGarageDTO;
import uni.project.rest.api.model.UpdateGarageDTO;
import uni.project.rest.api.repository.GarageRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GarageService {

    private GarageRepository garageRepository;

    private EntityManager entityManager;

    public ResponseGarageDTO getGarageById(Long garageId) {
        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new ResourceNotFoundException404("Garage not found"));

        return new ResponseGarageDTO(
                garage.getId(),
                garage.getName(),
                garage.getLocation(),
                garage.getCity(),
                garage.getCapacity()
        );
    }

    @Transactional
    public ResponseGarageDTO updateGarage(Long garageId, UpdateGarageDTO updateGarageDTO) {
        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new ResourceNotFoundException404("Garage not found"));

        garage.setName(updateGarageDTO.getName());
        garage.setLocation(updateGarageDTO.getLocation());
        garage.setCapacity(updateGarageDTO.getCapacity());
        garage.setCity(updateGarageDTO.getCity());

        garageRepository.save(garage);

        return new ResponseGarageDTO(
                garage.getId(),
                garage.getName(),
                garage.getLocation(),
                garage.getCity(),
                garage.getCapacity()
        );
    }

    @Transactional
    public void deleteGarageById(Long garageId) {
//        garageRepository.delete(getGarageByIdForDelete(garageId));
        Garage garageToDelete = garageRepository.findById(garageId).orElseThrow(() -> new ResourceNotFoundException404("Garage not found"));
        garageRepository.delete(garageToDelete);
    }

    public List<Garage> getAllGaragesByCity(String city) {

            TypedQuery<Garage> filteredGarages = entityManager.createQuery(
                    "select e from Garage e where e.city = :city", Garage.class
            );
            filteredGarages.setParameter("city", city);
            List<Garage> resultList = filteredGarages.getResultList();
            return resultList;

    }

    public List<Garage> getAllGarages() {
        return garageRepository.findAll();
    }

    public Garage getGarageByIdForDelete(Long garageId) {
        return entityManager.find(Garage.class, garageId);
    }

    @Transactional
    public Garage createGarage(CreateGarageDTO createGarageDTO){

        Garage garage = new Garage();
        garage.setName(createGarageDTO.getName());
        garage.setLocation(createGarageDTO.getLocation());
        garage.setCity(createGarageDTO.getCity());
        garage.setCapacity(createGarageDTO.getCapacity());
        entityManager.persist(garage);
        return garage;

    }


    public List<GarageDailyAvailabilityReportDTO> getGarageDailyAvailabilityReport(Long garageId, LocalDate startDate, LocalDate endDate) {
        List<Object[]> reportData = garageRepository.findGarageDailyAvailabilityReport(garageId, startDate, endDate);

        return reportData.stream()
                .map(data -> {
                    LocalDate date = (LocalDate) data[0];
                    Long requests = (Long) data[1];

                    Garage garage = garageRepository.findById(garageId)
                            .orElseThrow(() -> new RuntimeException("Garage not found"));
                    int availableCapacity = garage.getCapacity() - requests.intValue();

                    return new GarageDailyAvailabilityReportDTO(date, requests.intValue(), availableCapacity);
                })
                .collect(Collectors.toList());
    }


    public ResponseGarageDTO mapGarageToResponseDTO(Garage garage) {
        return new ResponseGarageDTO(
                garage.getId(),
                garage.getName(),
                garage.getLocation(),
                garage.getCity(),
                garage.getCapacity()
        );
    }

}
