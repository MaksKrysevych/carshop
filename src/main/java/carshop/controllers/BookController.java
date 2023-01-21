package carshop.controllers;

import carshop.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {
    private final RequestService requestService;

    @Autowired
    public BookController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/book/{id}")
    public String book(@PathVariable("id") Long advert_id, Authentication authentication){
        requestService.book(advert_id, authentication);

        return "redirect:/requests";
    }
}