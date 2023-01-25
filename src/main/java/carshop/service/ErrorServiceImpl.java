package carshop.service;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ErrorServiceImpl implements ErrorService {
    @Override
    public String getError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {

            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return pageNotFound();
            }
            else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return noPermission();
            }
        }

        return unknownError();
    }

    @Override
    public String pageNotFound() {
        return "404";
    }

    @Override
    public String noPermission() {
        return "403";
    }

    @Override
    public String unknownError() {
        return "error";
    }
}
