package uni.project.rest.api.service.implementations;

import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.project.rest.api.dao.interfaces.GarageDAO;
import uni.project.rest.api.entity.Garage;
import uni.project.rest.api.service.interfaces.GarageService;

import java.util.Date;
import java.util.List;

@Service
public class GarageServiceImpl implements GarageService {
    private GarageDAO garageDAO;

    @Override
    public Garage getGarageById(int theId) {
        return garageDAO.getGarageById(theId);
    }

    @Override
    @Transactional
    public Garage updateGarageById(Garage theGarage, int theId) {
        return garageDAO.updateGarageById(theGarage, theId);
    }

    @Override
    @Transactional
    public void deleteGarageById(int theId) {
        garageDAO.deleteGarageById(theId);
    }

    @Override
    public List<Garage> getAllGaragesByCity(String theCity) {
        return garageDAO.getAllGaragesByCity(theCity);
    }

    @Override
    public Garage getGarageDailyReport(int theId, Date startDate, Date endDate) {
        return null; //TODO
    }

    @Override
    @Transactional
    public Garage createGarage(Garage theGarage) {
        return garageDAO.createGarage(theGarage);
    }

    @Autowired
    public GarageServiceImpl(GarageDAO garageDAO) {
        this.garageDAO = garageDAO;
    }

    @Override
    public List<Garage> getAllGarages() {
        return garageDAO.getAllGarages();
    }

}
