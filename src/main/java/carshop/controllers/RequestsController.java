package carshop.controllers;

import carshop.model.entity.Storage;
import carshop.service.RequestService;
import carshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequestsController {
    private final RequestService requestService;
    private final StorageService storageService;

    @Autowired
    public RequestsController(RequestService requestService, StorageService storageService) {
        this.requestService = requestService;
        this.storageService = storageService;
    }

    @GetMapping("/requests")
    public String getRequests(Model model, Authentication authentication){
        model.addAttribute("requests", requestService.getAllRequestsForUser(authentication.getName()));
        model.addAttribute("storages", storageService.getAllStorageAdverts());

        return "requests";
    }

    @PostMapping("/requests")
    public String changeStatusOfRequest(@ModelAttribute Storage updatedStatus){
        storageService.updateStorageAdvert(updatedStatus);

        return "redirect:/requests";
    }

    @GetMapping("/request/delete/{id}")
    public String deleteRequestById(@PathVariable Long id){
        requestService.deleteRequestById(id);

        return "redirect:/requests";
    }

    @GetMapping("/request/reserve/{id}")
    public String reserveCarById(@PathVariable Long id){
        storageService.reserveCarById(id);

        return "redirect:/requests";
    }
}