package uni.project.rest.api.dao.interfaces;

import uni.project.rest.api.entity.Garage;

import java.util.Date;
import java.util.List;

public interface GarageDAO {

    Garage getGarageById(int theId);
    Garage updateGarageById(Garage theGarage, int theId);
    void deleteGarageById(int theId);
    List<Garage> getAllGaragesByCity(String theCity);
    Garage getGarageDailyReport(int theId, Date startDate, Date endDate);
    Garage createGarage(Garage theGarage);

    List<Garage> getAllGarages();

}
