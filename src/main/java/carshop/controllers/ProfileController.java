package carshop.controllers;

import carshop.model.entity.User;
import carshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model){
        model.addAttribute("user", userService.getUserByEmail(authentication.getName()));

        return "profile";
    }

    @PostMapping("/profile/edit")
    public String edit(@ModelAttribute User editedUser, Authentication authentication){
        userService.editUser(editedUser, authentication);

        return "redirect:/profile";
    }

    @PostMapping("/profile/top-up")
    public String topUp(@ModelAttribute User user, Authentication authentication){
        userService.topUp(user, authentication);

        return "redirect:/profile";
    }
}
