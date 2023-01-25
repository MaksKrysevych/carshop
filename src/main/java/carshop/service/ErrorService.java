package carshop.service;

import jakarta.servlet.http.HttpServletRequest;

public interface ErrorService {
    String getError(HttpServletRequest request);

    String pageNotFound();

    String noPermission();

    String unknownError();
}
