package carshop.controllers;

import carshop.model.entity.User;
import carshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{page}")
    public String getUsers(@PathVariable(value = "page") int pageNo, Model model){
        int recordsPerPage = 2;
        int rows = userService.getAllUsers().size();
        int noOfPages = rows / recordsPerPage;

        if (rows % recordsPerPage != 0) {
            noOfPages++;
        }

        model.addAttribute("users", userService.getAllUsersByPage(pageNo, recordsPerPage));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", noOfPages);

        return "users";
    }

    @PostMapping("/users")
    public String updateRoleUsers(@ModelAttribute User user){
        userService.updateRoleUsers(user);

        return "redirect:/users/1";
    }
}