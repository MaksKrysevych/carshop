package carshop.service;

import carshop.model.entity.Advertisement;
import carshop.repository.AdvertisementRepository;
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

class AdvertisementServiceTest {

    @Mock
    AdvertisementRepository advertisementRepository;

    @InjectMocks
    AdvertisementServiceImpl advertisementService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    static List<Advertisement> advertisementList = new ArrayList<>();

    static {
        advertisementList.add(new Advertisement(1L,1L,1L,10000,"asfdg"));
        advertisementList.add(new Advertisement(1L,1L,1L,10000,"safdgfasfdg"));
    }

    @Test
    void getAllAdverts() {
        when(advertisementRepository.getAllAdverts()).thenReturn(advertisementList);
        assertEquals(2, advertisementService.getAllAdverts().size());
    }

    @Test
    void getAdvertById() {
        when(advertisementRepository.getAllAdverts()).thenReturn(advertisementList);
        assertEquals(2, advertisementService.getAllAdverts().size());
    }

    @Test
    void createAdvert() {
        when(advertisementRepository.createAdvert(new Advertisement(1L,1L,1L,10000,"asfdg"))).thenReturn(advertisementList.get(0));
        assertEquals(advertisementList.get(0), advertisementService.createAdvert(new Advertisement(1L,1L,1L,10000,"asfdg")));
    }

    @Test
    void updateAdvert() {
        when(advertisementRepository.updateAdvert(new Advertisement(1L,1L,1L,10000,"safdgfasfdg"))).thenReturn(advertisementList.get(1));
        assertEquals(advertisementList.get(1).getDescription(), advertisementService.updateAdvert(new Advertisement(1L,1L,1L,10000,"safdgfasfdg")).getDescription());
    }

    @Test
    void deleteAdvertById() {
        advertisementRepository.deleteAdvertById(1L);
        assertEquals(0, advertisementService.getAllAdverts().size());
        verify(advertisementRepository).deleteAdvertById(1L);
    }
}