package uni.project.rest.api.dao.interfaces;

import uni.project.rest.api.entity.Car;

import java.util.List;

public interface CarDAO {

    Car createCar(Car car);
    List<Car> getAllCars(String make, long garage, int fromYear, int toYear);
    Car getCarById(int id);
    Car updateCar(int id,Car car);
    void deleteCarById(int id);

}
