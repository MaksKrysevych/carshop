package carshop.controllers;

import carshop.model.entity.Request;
import carshop.model.enums.Statuses;
import carshop.service.RequestService;
import carshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
public class BookController {
    private final RequestService requestService;
    private final StorageService storageService;

    @Autowired
    public BookController(RequestService requestService, StorageService storageService) {
        this.requestService = requestService;
        this.storageService = storageService;
    }

    @GetMapping("/book/{id}")
    public String book(@PathVariable("id") Long advert_id, Authentication authentication){
        requestService.createRequest(new Request(1L, advert_id, authentication.getName(), Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())), storageService.getStorageCarById(advert_id).getStatus()));

        return "redirect:/requests";
    }
}