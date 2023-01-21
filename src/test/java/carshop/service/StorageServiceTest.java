package carshop.service;

import carshop.model.entity.Storage;
import carshop.model.enums.Statuses;
import carshop.repository.StorageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StorageServiceTest {
    @Mock
    StorageRepository storageRepository;

    @InjectMocks
    StorageServiceImpl storageService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    static List<Storage> storageList = new ArrayList<>();

    static {
        storageList.add(new Storage(1L, Statuses.AVAILIBLE.toString()));
        storageList.add(new Storage(1L, Statuses.BOOKED.toString()));
    }

    @Test
    void getAllStorageCars() {
        when(storageRepository.getAllStorages()).thenReturn(storageList);
        assertEquals(2, storageService.getAllStorageAdverts().size());
    }

    @Test
    void getStorageCarById() {
        when(storageRepository.getStorageById(1L)).thenReturn(storageList.get(0));
        assertEquals(storageList.get(0), storageService.getStorageAdvertById(1L));
    }

    @Test
    void createStorageCar() {
        when(storageRepository.createStorage(new Storage(1L, Statuses.AVAILIBLE.toString()))).thenReturn(storageList.get(0));
        assertEquals(storageList.get(0), storageService.createStorageAdvert(new Storage(1L, Statuses.AVAILIBLE.toString())));
    }

    @Test
    void updateStorageCar() {
        when(storageRepository.createStorage(new Storage(1L, Statuses.BOOKED.toString()))).thenReturn(storageList.get(1));
        assertEquals(storageList.get(1).getStatus(), storageService.createStorageAdvert(new Storage(1L, Statuses.BOOKED.toString())).getStatus());
    }

    @Test
    void deleteStorageCarById() {
        storageRepository.deleteStorageById(1L);
        assertEquals(0, storageService.getAllStorageAdverts().size());
        verify(storageRepository).deleteStorageById(1L);
    }
}