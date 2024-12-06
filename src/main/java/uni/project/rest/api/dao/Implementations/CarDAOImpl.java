package uni.project.rest.api.dao.Implementations;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uni.project.rest.api.dao.interfaces.CarDAO;
import uni.project.rest.api.entity.Car;

import java.util.List;

@Repository
public class CarDAOImpl implements CarDAO {

    //Define field for entity manager;
//    @PersistenceContext
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public CarDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Impl methods

    @Override
    public Car createCar(Car car) {
        entityManager.persist(car);
//        entityManager.merge(car);
        return car;
    }

    @Override
    public List<Car> getAllCars(String make, long garage, int fromYear, int toYear) {

//        TypedQuery<Car> query = entityManager.createQuery("SELECT c FROM Car c", Car.class);
        TypedQuery<Car> query = entityManager.createQuery(
                "SELECT c FROM Car c WHERE c.make = :make AND c.year BETWEEN :fromYear AND :toYear",
                Car.class
        );

        // Set the parameters
        query.setParameter("make", make);
        query.setParameter("fromYear", fromYear);
        query.setParameter("toYear", toYear);

        // Execute and return the results
        return query.getResultList();


    }

    @Override
    public Car getCarById(int id) {
        Car car =entityManager.find(Car.class, id);
        return car;
    }

    @Override
    public Car updateCar(int id,Car newCar) {
        return null;
    }

    @Override
    @Transactional
    public void deleteCarById(int id) {
        Car car = entityManager.find(Car.class, id);
        entityManager.remove(car);
    }
}
