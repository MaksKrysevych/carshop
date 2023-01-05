package carshop.controllers;

import carshop.model.entity.User;
import carshop.model.enums.Roles;
import carshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

   @GetMapping("/login")
    public String login(){
            return "login";
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model){
        model.addAttribute("user", new User());
        return "signUp";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@ModelAttribute User user){
        user.setRole(Roles.USER.toString());
        user.setAccount(0);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.createUser(user);
        return "redirect:/login";
    }
}
