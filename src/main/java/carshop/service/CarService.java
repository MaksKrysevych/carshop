package carshop.service;

import carshop.model.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    Car getCarById(Long id);

    Car createCar(Car car);

    Car updateCar(Car car);

    void deleteCarById(Long id);
}
