package uni.project.rest.api.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uni.project.rest.api.entity.Car;

import java.util.List;

@Repository
public class CarDAOImpl implements CarDAO {

    //Define field for entity manager;
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
    public List<Car> getAllCars() {

        // create a query
        TypedQuery<Car> query = entityManager.createQuery("FROM Car", Car.class);

        // execute query and get result list
        List<Car> cars = query.getResultList();

        // return the results
        return cars;

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
