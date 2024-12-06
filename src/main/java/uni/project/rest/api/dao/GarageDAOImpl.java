package uni.project.rest.api.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import uni.project.rest.api.entity.Garage;

import java.util.Date;
import java.util.List;

@Repository
public class GarageDAOImpl implements GarageDAO {
    @Override
    public Garage getGarageById(int theId) {
        return null;
    }

    @Override
    public Garage updateGarage(Garage theGarage) {
        return null;
    }

    @Override
    public void deleteGarage(Garage theGarage) {

    }

    @Override
    public List<Garage> getAllGaragesByCity(String theCity) {
        return List.of();
    }

    @Override
    public Garage getGarageDailyReport(int theId, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public Garage createGarage(Garage theGarage) {
        return null;
    }

    EntityManager entityManager;

    public GarageDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



}
