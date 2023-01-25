package carshop.controllers;

import carshop.service.ErrorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    private final ErrorService errorService;

    @Autowired
    public CustomErrorController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        return errorService.getError(request);
    }
}
