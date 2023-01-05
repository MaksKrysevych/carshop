package carshop.controllers;

import carshop.repository.CarRepository;
import carshop.repository.GalleryRepository;
import carshop.service.CarService;
import carshop.service.GalleryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CatalogController {
    private final CarService carService;
    private final GalleryService galleryService;

    public CatalogController(CarService carService, GalleryService galleryService) {
        this.carService = carService;
        this.galleryService = galleryService;
    }

    @GetMapping("/catalog")
    public String catalog(Model model){
        model.addAttribute("cars", new CarRepository().getAllCars());
        model.addAttribute("galleries", new GalleryRepository().getAllGalleries());

        return "catalog";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable(value = "id") Long car_id, Model model){
        model.addAttribute("car", carService.getCarById(car_id));

        return "details";
    }
}