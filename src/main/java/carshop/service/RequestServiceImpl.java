package carshop.service;

import carshop.model.entity.Request;
import carshop.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserService userService;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, UserService userService) {
        this.requestRepository = requestRepository;
        this.userService = userService;
    }

    @Override
    public List<Request> getAllRequests() {

        return requestRepository.getAllRequests();
    }

    @Override
    public List<Request> getAllRequestsForUser(String email) {
        if (userService.getUserByEmail(email).getRole().equals("USER")) {
            List<Request> requests = requestRepository.getAllRequests();
            List<Request> usersRequests = new ArrayList<>();
            for (var request : requests) {
                if (request.getEmail().equals(email)){
                    usersRequests.add(request);
                }
            }
            return usersRequests;
        }
        else {
            return requestRepository.getAllRequests();
        }
    }

    @Override
    public Request getRequestById(Long id) {
        return requestRepository.getRequestById(id);
    }

    @Override
    public Request createRequest(Request request) {
        return requestRepository.createRequest(request);
    }

    @Override
    public Request book(Long advert_id, Authentication authentication) {
        return requestRepository.createRequest(new Request(1L, advert_id, authentication.getName(), Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()))));
    }

    @Override
    public Request updateRequest(Request request) {
        return requestRepository.updateRequest(request);
    }

    @Override
    public void deleteRequestById(Long id) {
        requestRepository.deleteRequestById(id);
    }
}
