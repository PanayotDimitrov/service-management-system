package uni.project.rest.api.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.project.rest.api.entity.Garage;
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

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private EntityManager entityManager;


    public Garage addGarage(CreateGarageDTO createGarageDTO){

        Garage garage = new Garage();
        garage.setName(createGarageDTO.getName());
        garage.setLocation(createGarageDTO.getLocation());
        garage.setCity(createGarageDTO.getCity());
        garage.setCapacity(createGarageDTO.getCapacity());

//        return garageRepository.save(createGarageDTO);
        return garageRepository.save(garage);
    }

    public ResponseGarageDTO getGarageById(Long garageId) {
        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new RuntimeException("Garage not found"));

        return new ResponseGarageDTO(
                garage.getId(),
                garage.getName(),
                garage.getCity(),
                garage.getCapacity()
        );
    }

    public ResponseGarageDTO updateGarage(Long garageId, UpdateGarageDTO updateGarageDTO) {
        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new RuntimeException("Garage not found"));

        garage.setName(updateGarageDTO.getName());
        garage.setLocation(updateGarageDTO.getLocation());
        garage.setCapacity(updateGarageDTO.getCapacity());
        garage.setCity(updateGarageDTO.getCity());

        garageRepository.save(garage);

        return new ResponseGarageDTO(
                garage.getId(),
                garage.getName(),
                garage.getCity(),
                garage.getCapacity()
        );
    }
    public void deleteGarageById(Long garageId) {
        Garage garage = garageRepository.findById(garageId)
                .orElseThrow(() -> new RuntimeException("Garage not found"));

        garageRepository.delete(garage);
    }


//    public List<ResponseGarageDTO> getAllGaragesByCity(String city) {
//        List<ResponseGarageDTO> garages = garageRepository.findByCity(city);
//        return garages.stream()
//                .map(garage -> new ResponseGarageDTO(
//                        garage.getId(),
//                        garage.getName(),
//                        garage.getCity(),
//                        garage.getCapacity()))
//                .collect(Collectors.toList());
//    }
    public List<Garage> getAllGaragesByCity(String city) {
        TypedQuery<Garage> filteredGarages = entityManager.createQuery(
                "select e from Garage e where e.city = :city", Garage.class
        );
        filteredGarages.setParameter("city", city);
        return filteredGarages.getResultList();
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
                garage.getCity(),
                garage.getCapacity()
        );
    }

}
