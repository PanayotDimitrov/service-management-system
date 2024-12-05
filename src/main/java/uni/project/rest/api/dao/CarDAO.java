package uni.project.rest.api.dao;

import uni.project.rest.api.entity.Car;

import java.util.List;

public interface CarDAO {

    Car createCar(Car car);
    List<Car> getAllCars();
    Car getCarById(int id);
    Car updateCar(Car car);
    void deleteCarById(int id);

}
