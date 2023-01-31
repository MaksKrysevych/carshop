package carshop.service;

import carshop.model.entity.Car;
import carshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    @Override
    public List<Car> getCarsByPage(int page, int carsPerPage) {
        return carRepository.getCarsByPage(page, carsPerPage);
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.getCarById(id);
    }

    @Override
    public Car createCar(Car car) {
        return carRepository.createCar(car);
    }

    @Override
    public Car updateCar(Car car) {
        return carRepository.updateCar(car);
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteCarById(id);
    }
}