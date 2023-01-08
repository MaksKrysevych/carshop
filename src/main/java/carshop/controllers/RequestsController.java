package carshop.controllers;

import carshop.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RequestsController {
    private final RequestService requestService;

    @Autowired
    public RequestsController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/requests")
    public String getRequests(Model model){
        model.addAttribute("requests", requestService.getAllRequests());

        return "requests";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteRequestById(@PathVariable Long id){
        requestService.deleteRequestById(id);

        return "redirect:/requests";
    }
}