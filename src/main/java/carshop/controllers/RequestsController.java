package carshop.controllers;

import carshop.model.entity.Request;
import carshop.model.enums.Statuses;
import carshop.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/requests")
    public String changeStatusOfRequest(@ModelAttribute Request updatedRequest){
        Request update = requestService.getRequestById(updatedRequest.getRequestId());
        update.setStatus(updatedRequest.getStatus());
        requestService.updateRequest(update);

        return "requests";
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