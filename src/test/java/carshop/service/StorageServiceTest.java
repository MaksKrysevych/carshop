package carshop.service;

import carshop.model.entity.Storage;
import carshop.repository.StorageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        storageList.add(new Storage(1L, 10));
        storageList.add(new Storage(1L, 1));
    }

    @Test
    void getAllStorages() {
        when(storageRepository.getAllStorages()).thenReturn(storageList);
        assertEquals(2, storageService.getAllStorages().size());
    }

    @Test
    void getStorageById() {
        when(storageRepository.getStorageById(1L)).thenReturn(storageList.get(0));
        assertEquals(storageList.get(0), storageService.getStorageById(1L));
    }

    @Test
    void createStorage() {
        when(storageRepository.createStorage(new Storage(1L, 10))).thenReturn(storageList.get(0));
        assertEquals(storageList.get(0), storageService.createStorage(new Storage(1L, 10)));
    }

    @Test
    void updateStorage() {
        when(storageRepository.createStorage(new Storage(1L, 1))).thenReturn(storageList.get(1));
        assertEquals(storageList.get(1).getQuantity(), storageService.createStorage(new Storage(1L, 1)).getQuantity());
    }

    @Test
    void deleteStorageById() {
        storageRepository.deleteStorageById(1L);
        assertEquals(0, storageService.getAllStorages().size());
        verify(storageRepository).deleteStorageById(1L);
    }
}