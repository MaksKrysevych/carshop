package carshop.controllers;

import carshop.model.entity.Advertisement;
import carshop.service.AdvertisementService;
import carshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdvertisementController {
    private final AdvertisementService advertisementService;
    private final StorageService storageService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService, StorageService storageService) {
        this.advertisementService = advertisementService;
        this.storageService = storageService;
    }

    @GetMapping("/advertisements/{page}")
    public String getAdverts(@PathVariable(value = "page") int pageNo, Model model){
        int recordsPerPage = 2;
        int rows = advertisementService.getAllAdverts().size();
        int noOfPages = rows / recordsPerPage;

        if (rows % recordsPerPage != 0) {
            noOfPages++;
        }

        model.addAttribute("adverts", advertisementService.getAdvertsByPage(pageNo, recordsPerPage, "advertId"));
        model.addAttribute("advertisement", new Advertisement());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("sorting", "advertId");
        model.addAttribute("totalPages", noOfPages);

        return "advertisements";
    }

    @PostMapping("/advertisement")
    public String createAdvert(@ModelAttribute Advertisement advertisement){
        advertisementService.createAdvert(advertisement);

        return "redirect:/advertisements/1";
    }

    @GetMapping("/advertisements/update/{id}")
    public String updateAdvert(@PathVariable Long id, Model model){
        model.addAttribute("advert", advertisementService.getAdvertById(id));

        return "updateAdvert";
    }

    @PostMapping("/advertisements/update")
    public String updateAdvert(@ModelAttribute Advertisement advertisement){
        advertisementService.updateAdvert(advertisement);

        return "redirect:/advertisements/1";
    }

    @GetMapping("/advertisements/delete/{id}")
    public String deleteAdvertById(@PathVariable Long id){
        advertisementService.deleteAdvertById(id);
        storageService.deleteStorageAdvertById(id);

        return "redirect:/advertisements/1";
    }
}