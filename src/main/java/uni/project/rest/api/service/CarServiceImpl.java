package uni.project.rest.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.project.rest.api.dao.CarDAO;
import uni.project.rest.api.entity.Car;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {


    private CarDAO carDAO;

    @Override
    @Transactional
    public Car createCar(Car car) {
        return carDAO.createCar(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    @Override
    public Car getCarById(int id) {
        return carDAO.getCarById(id);
    }

    @Override
    @Transactional
    public Car updateCar(int id, Car newCar) {
        Car getCar = carDAO.getCarById(id);
        getCar.setLicensePlate(newCar.getLicensePlate());
        getCar.setModel(newCar.getModel());
        getCar.setYear(newCar.getYear());
        getCar.setMake(newCar.getMake());
        return getCar;
    }

    @Override
    public void deleteCarById(int id) {
        carDAO.deleteCarById(id);
    }

    @Autowired
    public CarServiceImpl(CarDAO carDAO) {
        this.carDAO = carDAO;
    }


}
