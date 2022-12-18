package carshop.service;

import carshop.model.entity.Car;
import carshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CarServiceTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarServiceImpl carService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    static List<Car> carList = new ArrayList<>();

    static {
        carList.add(new Car(1L, "as", "as", "as", "as", Date.valueOf("2022-02-02")));
        carList.add(new Car(1L, "as", "as", "as", "as", Date.valueOf("2022-02-22")));
    }

    @Test
    void getAllCars() {
        when(carRepository.getAllCars()).thenReturn(carList);
        assertEquals(2, carService.getAllCars().size());
    }

    @Test
    void getCarById() {
        when(carRepository.getCarById(1L)).thenReturn(carList.get(0));
        assertEquals(carList.get(0), carService.getCarById(1L));
    }

    @Test
    void createCar() {
        when(carRepository.createCar(new Car(1L, "as", "as", "as", "as", Date.valueOf("2022-02-02")))).thenReturn(carList.get(0));
        assertEquals(carList.get(0), carService.createCar(new Car(1L, "as", "as", "as", "as", Date.valueOf("2022-02-02"))));
    }

    @Test
    void updateCar() {
        when(carRepository.createCar(new Car(1L, "as", "as", "as", "as", Date.valueOf("2022-02-22")))).thenReturn(carList.get(1));
        assertEquals(carList.get(1).getGraduationYear(), carService.createCar(new Car(1L, "as", "as", "as", "as", Date.valueOf("2022-02-22"))).getGraduationYear());
    }

    @Test
    void deleteCarById() {
        carRepository.deleteCarById(1L);
        assertEquals(0, carService.getAllCars().size());
        verify(carRepository).deleteCarById(1L);
    }
}