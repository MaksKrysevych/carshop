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
    public String edit(@ModelAttribute User user, Authentication authentication){
        User user1 = userService.getUserByEmail(authentication.getName());
        user1.setName(user.getName());
        user1.setSurname(user.getSurname());
        user1.setPhone(user.getPhone());

        userService.updateUser(user1);

        return "redirect:/profile";
    }

    @PostMapping("/profile/top-up")
    public String topUp(@ModelAttribute User user, Authentication authentication){
        User user1 = userService.getUserByEmail(authentication.getName());
        user1.setAccount(user.getAccount() + user1.getAccount());
        userService.updateUser(user1);

        return "redirect:/profile";
    }
}
