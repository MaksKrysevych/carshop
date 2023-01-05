package carshop.service;

import carshop.model.entity.Request;
import carshop.model.enums.Statuses;
import carshop.repository.RequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RequestServiceTest {

    @Mock
    RequestRepository requestRepository;

    @InjectMocks
    RequestServiceImpl requestService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    static List<Request> requestList = new ArrayList<>();

    static {
        requestList.add(new Request(1L, 1L, "1", Date.valueOf("2022-02-02"), Statuses.CREATED.toString()));
        requestList.add(new Request(1L, 1L, "1", Date.valueOf("2022-02-22"), Statuses.AVAILIBLE.toString()));
    }

    @Test
    void getAllRequests() {
        when(requestRepository.getAllRequests()).thenReturn(requestList);
        assertEquals(2, requestService.getAllRequests().size());
    }

    @Test
    void getRequestById() {
        when(requestRepository.getRequestById(1L)).thenReturn(requestList.get(0));
        assertEquals(requestList.get(0), requestService.getRequestById(1L));
    }

    @Test
    void createRequest() {
        when(requestRepository.createRequest(new Request(1L, 1L, "1", Date.valueOf("2022-02-02"), Statuses.CREATED.toString()))).thenReturn(requestList.get(0));
        assertEquals(requestList.get(0), requestService.createRequest(new Request(1L, 1L, "1", Date.valueOf("2022-02-02"), Statuses.CREATED.toString())));
    }

    @Test
    void updateRequest() {
        when(requestRepository.createRequest(new Request(1L, 1L, "1", Date.valueOf("2022-02-22"), Statuses.AVAILIBLE.toString()))).thenReturn(requestList.get(1));
        assertEquals(requestList.get(1).getStatus(), requestService.createRequest(new Request(1L, 1L, "1", Date.valueOf("2022-02-22"), Statuses.AVAILIBLE.toString())).getStatus());

    }

    @Test
    void deleteRequestById() {
        requestRepository.deleteRequestById(1L);
        assertEquals(0, requestService.getAllRequests().size());
        verify(requestRepository).deleteRequestById(1L);
    }
}