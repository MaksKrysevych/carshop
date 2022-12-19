package carshop.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/car-shop"})
    public String carShop(){
        return "carShop";
    }

    @GetMapping("/catalog")
    public String catalog(){
        return "catalog";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUp(){
        return "signUp";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }
}