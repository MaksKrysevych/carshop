package carshop.controllers;

import carshop.model.entity.Advertisement;
import carshop.service.AdvertisementService;
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

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/advertisements")
    public String getAdverts(Model model){
        model.addAttribute("adverts", advertisementService.getAllAdverts());
        model.addAttribute("advertisement", new Advertisement());

        return "advertisements";
    }

    @PostMapping("/advertisement")
    public String createAdvert(@ModelAttribute Advertisement advertisement){
        advertisementService.createAdvert(advertisement);

        return "redirect:/advertisements";
    }

    @GetMapping("/advertisements/update/{id}")
    public String updateAdvert(@PathVariable Long id, Model model){
        model.addAttribute("advert", advertisementService.getAdvertById(id));

        return "updateAdvert";
    }

    @PostMapping("/advertisements/update")
    public String updateAdvert(@ModelAttribute Advertisement advertisement){
        advertisementService.updateAdvert(advertisement);

        return "redirect:/advertisements";
    }

    @GetMapping("/advertisements/delete/{id}")
    public String deleteAdvertById(@PathVariable Long id){
        advertisementService.deleteAdvertById(id);

        return "redirect:/advertisements";
    }
}
