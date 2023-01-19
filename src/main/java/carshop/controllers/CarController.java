package carshop.controllers;

import carshop.model.entity.Advertisement;
import carshop.model.entity.Car;
import carshop.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getCars(Model model){
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("car", new Car());

        return "cars";
    }

    @PostMapping("/car")
    public String createCar(@ModelAttribute Car car){
        carService.createCar(car);

        return "redirect:/cars";
    }

    @GetMapping("/cars/update/{id}")
    public String updateCar(@PathVariable Long id, Model model){
        model.addAttribute("car", carService.getCarById(id));

        return "updateCar";
    }

    @PostMapping("/car/update")
    public String updateAdvert(@ModelAttribute Car car){
        carService.updateCar(car);

        return "redirect:/cars";
    }

    @GetMapping("/cars/delete/{id}")
    public String getCars(@PathVariable Long id){
        carService.deleteCarById(id);

        return "redirect:/cars";
    }
}
