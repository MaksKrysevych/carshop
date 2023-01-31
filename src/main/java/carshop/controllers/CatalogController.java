package carshop.controllers;

import carshop.service.AdvertisementService;
import carshop.service.CarService;
import carshop.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CatalogController {
    private final AdvertisementService advertisementService;
    private final CarService carService;
    private final GalleryService galleryService;

    @Autowired
    public CatalogController(AdvertisementService advertisementService, CarService carService, GalleryService galleryService) {
        this.advertisementService = advertisementService;
        this.carService = carService;
        this.galleryService = galleryService;
    }

    @GetMapping("/catalog/{page}")
    public String catalog(@PathVariable(value = "page") int pageNo, Model model){
        int recordsPerPage = 2;
        int rows = advertisementService.getAllAdverts().size();
        int noOfPages = rows / recordsPerPage;

        if (rows % recordsPerPage != 0) {
            noOfPages++;
        }

        model.addAttribute("adverts", advertisementService.getAdvertsByPage(pageNo, 2, "advertId"));
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("galleries", galleryService.getAllGalleries());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("sorting", "advertId");
        model.addAttribute("totalPages", noOfPages);

        return "catalog";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long advert_id, Model model){
        model.addAttribute("advert", advertisementService.getAdvertById(advert_id));
        model.addAttribute("car", carService.getCarById(advertisementService.getAdvertById(advert_id).getCarId()));
        model.addAttribute("gallery", galleryService.getGalleryById(advertisementService.getAdvertById(advert_id).getPhotoId()));

        return "details";
    }
}