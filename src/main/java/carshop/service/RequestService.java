package carshop.service;

import carshop.model.entity.Request;

import java.util.List;

public interface RequestService {
    List<Request> getAllRequests();

    Request getRequestById(Long id);

    Request createRequest(Request request);

    Request updateRequest(Request request);

    void deleteRequestById(Long id);
}