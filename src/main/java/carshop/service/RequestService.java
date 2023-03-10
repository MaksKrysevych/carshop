package carshop.service;

import carshop.model.entity.Request;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface RequestService {
    List<Request> getAllRequests();

    List<Request> getAllRequests(String email, int page, int requestsPerPage);

    Request getRequestById(Long id);

    Request createRequest(Request request);

    Request book(Long advert_id, Authentication authentication);

    Request updateRequest(Request request);

    void deleteRequestById(Long id);
}