package carshop.controllers;

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

    @GetMapping("/cars/{page}")
    public String getCars(@PathVariable(value = "page") int pageNo, Model model){
        int recordsPerPage = 2;
        int rows = carService.getAllCars().size();
        int noOfPages = rows / recordsPerPage;

        if (rows % recordsPerPage != 0) {
            noOfPages++;
        }

        model.addAttribute("cars", carService.getCarsByPage(pageNo,recordsPerPage));
        model.addAttribute("car", new Car());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", noOfPages);
        return "cars";
    }

    @PostMapping("/car")
    public String createCar(@ModelAttribute Car car){
        carService.createCar(car);

        return "redirect:/cars/1";
    }

    @GetMapping("/cars/update/{id}")
    public String updateCar(@PathVariable Long id, Model model){
        model.addAttribute("car", carService.getCarById(id));

        return "updateCar";
    }

    @PostMapping("/car/update")
    public String updateCar(@ModelAttribute Car car){
        carService.updateCar(car);

        return "redirect:/cars/1";
    }

    @GetMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable Long id){
        carService.deleteCarById(id);

        return "redirect:/cars/1";
    }
}
