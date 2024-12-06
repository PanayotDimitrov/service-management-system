package uni.project.rest.api.service.implementations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.project.rest.api.dao.interfaces.CarDAO;
import uni.project.rest.api.entity.Car;
import uni.project.rest.api.service.interfaces.CarService;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDAO carDAO;

    @Autowired
    public CarServiceImpl(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    @Transactional
    public Car createCar(Car car) {
        return carDAO.createCar(car);
    }

    @Override
    public List<Car> getAllCars(String make, long garage, int fromYear, int toYear) {
        return carDAO.getAllCars(make, garage, fromYear, toYear);
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
    @Transactional
    public void deleteCarById(int id) {
        carDAO.deleteCarById(id);
    }


}
