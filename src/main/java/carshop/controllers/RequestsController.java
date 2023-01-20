package carshop.controllers;

import carshop.model.entity.Request;
import carshop.model.entity.Storage;
import carshop.model.enums.Statuses;
import carshop.service.RequestService;
import carshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getRequests(Model model){
        model.addAttribute("requests", requestService.getAllRequests());
        model.addAttribute("storages", storageService.getAllStorageCars());

        return "requests";
    }

    @PostMapping("/requests")
    public String changeStatusOfRequest(@ModelAttribute Storage updatedStatus){
        storageService.updateStorageCar(updatedStatus);

        return "redirect:/requests";
    }

    @GetMapping("/request/delete/{id}")
    public String deleteRequestById(@PathVariable Long id){
        requestService.deleteRequestById(id);

        return "redirect:/requests";
    }

    @GetMapping("/request/reserve/{id}")
    public String bookRequestById(@PathVariable Long id){
        Request request = requestService.getRequestById(id);
        request.setStatus(Statuses.BOOKED.toString());
        requestService.updateRequest(request);

        return "redirect:/requests";
    }
}