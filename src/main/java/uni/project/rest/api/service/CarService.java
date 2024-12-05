package uni.project.rest.api.service;

import uni.project.rest.api.entity.Car;

import java.util.List;

public interface CarService {

Car createCar(Car car);
List<Car> getAllCars();
Car getCarById(int id);
Car updateCar(int id,Car newCar);
void deleteCarById(int id);

}
