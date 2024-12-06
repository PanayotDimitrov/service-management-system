package uni.project.rest.api.dao.Implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import uni.project.rest.api.dao.interfaces.GarageDAO;
import uni.project.rest.api.entity.Garage;

import java.util.Date;
import java.util.List;

@Repository
public class GarageDAOImpl implements GarageDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Garage getGarageById(int theId) {
        Garage theGarage = entityManager.find(Garage.class, theId);
        return theGarage;
    }

    @Override
    public Garage updateGarageById(Garage theGarage, int theId) {
        Garage dbGarage = entityManager.find(Garage.class, theId);
        dbGarage.setCapacity(theGarage.getCapacity());
        dbGarage.setCity(theGarage.getCity());
        dbGarage.setLocation(theGarage.getLocation());
        dbGarage.setName(theGarage.getName());
        entityManager.persist(dbGarage);
        return dbGarage;
    }

    @Override
    public void deleteGarageById(int theId) {
        Garage garageToDelete = entityManager.find(Garage.class, theId);
        entityManager.remove(garageToDelete);
    }

    @Override
    public List<Garage> getAllGaragesByCity(String theCity) {
        TypedQuery<Garage> filteredGarages = entityManager.createQuery(
                "select e from Garage e where e.city = :city", Garage.class
        );
        filteredGarages.setParameter("city", theCity);
        return filteredGarages.getResultList();
    }

    @Override
    public Garage getGarageDailyReport(int theId, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public Garage createGarage(Garage theGarage) {
        entityManager.persist(theGarage);
        return theGarage;
    }


    public GarageDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Garage> getAllGarages() {
        TypedQuery<Garage> query = entityManager.createQuery("from Garage", Garage.class);
        return query.getResultList();
    }



}
