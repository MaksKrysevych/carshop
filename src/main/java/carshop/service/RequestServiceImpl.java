package carshop.service;

import carshop.model.entity.Request;
import carshop.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.getAllRequests();
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
    public Request updateRequest(Request request) {
        return requestRepository.updateRequest(request);
    }

    @Override
    public void deleteRequestById(Long id) {
        requestRepository.deleteRequestById(id);
    }
}
