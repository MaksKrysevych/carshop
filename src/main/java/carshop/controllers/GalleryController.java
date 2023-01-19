package carshop.controllers;

import carshop.model.entity.Advertisement;
import carshop.model.entity.Gallery;
import carshop.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GalleryController {
    private final GalleryService galleryService;

    @Autowired
    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping("/gallery")
    public String getPhotosFromGallery(Model model){
        model.addAttribute("gallery", galleryService.getAllGalleries());
        model.addAttribute("photo", new Gallery());

        return "gallery";
    }

    @PostMapping("/photo")
    public String createAdvert(@ModelAttribute Gallery photo){
        galleryService.createGallery(photo);

        return "redirect:/gallery";
    }

    @GetMapping("/photo/update/{id}")
    public String updateGallery(@PathVariable Long id, Model model){
        model.addAttribute("photo", galleryService.getGalleryById(id));

        return "updateGallery";
    }

    @PostMapping("/photo/update")
    public String updateGallery(@ModelAttribute Gallery photo){
        galleryService.updateGallery(photo);

        return "redirect:/gallery";
    }

    @GetMapping("/photo/delete/{id}")
    public String deleteGalleryById(@PathVariable Long id){
        galleryService.deleteGalleryById(id);

        return "redirect:/gallery";
    }
}
