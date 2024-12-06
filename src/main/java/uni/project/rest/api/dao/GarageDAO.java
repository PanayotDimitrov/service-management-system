package uni.project.rest.api.dao;

import uni.project.rest.api.entity.Garage;

import java.util.Date;
import java.util.List;

public interface GarageDAO {

    Garage getGarageById(int theId);
    Garage updateGarage(Garage theGarage);
    void deleteGarage(Garage theGarage);
    List<Garage> getAllGaragesByCity(String theCity);
    Garage getGarageDailyReport(int theId, Date startDate, Date endDate);
    Garage createGarage(Garage theGarage);


//    Date date = new Date(2000,10,10);
}
