package carshop.service;

import carshop.model.entity.Car;
import org.springframework.ui.Model;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    List<Car> getCarsByPage(int page, int carsPerPage);

    Car getCarById(Long id);

    Car createCar(Car car);

    Car updateCar(Car car);

    void deleteCarById(Long id);
}
