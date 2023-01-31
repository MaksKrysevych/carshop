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

    @GetMapping("/requests/{page}")
    public String getRequests(@PathVariable(value = "page") int pageNo, Model model, Authentication authentication){
        int recordsPerPage = 2;
        int rows = requestService.getAllRequests().size();
        int noOfPages = rows / recordsPerPage;

        if (rows % recordsPerPage != 0) {
            noOfPages++;
        }

        model.addAttribute("requests", requestService.getAllRequests(authentication.getName(), pageNo, recordsPerPage));
        model.addAttribute("storages", storageService.getAllStorageAdverts());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", noOfPages);

        return "requests";
    }

    @PostMapping("/requests")
    public String changeStatusOfRequest(@ModelAttribute Storage updatedStatus){
        storageService.updateStorageAdvert(updatedStatus);

        return "redirect:/requests/1";
    }

    @GetMapping("/request/delete/{id}")
    public String deleteRequestById(@PathVariable Long id){
        requestService.deleteRequestById(id);

        return "redirect:/requests/1";
    }

    @GetMapping("/request/reserve/{id}")
    public String reserveCarById(@PathVariable Long id){
        storageService.reserveCarById(id);

        return "redirect:/requests/1";
    }
}