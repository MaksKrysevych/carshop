package carshop.service;

import carshop.model.entity.Gallery;
import carshop.repository.GalleryRepository;
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

class GalleryServiceTest {
    @Mock
    GalleryRepository galleryRepository;

    @InjectMocks
    GalleryServiceImpl galleryService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    static List<Gallery> galleryList = new ArrayList<>();

    static {
        galleryList.add(new Gallery(1L,1L,"asfdg"));
        galleryList.add(new Gallery(1L,1L,"safdgfasfdg"));
    }

    @Test
    void getAllGalleries() {
        when(galleryRepository.getAllGalleries()).thenReturn(galleryList);
        assertEquals(2, galleryService.getAllGalleries().size());
    }

    @Test
    void getGalleryById() {
        when(galleryRepository.getGalleryById(1L)).thenReturn(galleryList.get(0));
        assertEquals(galleryList.get(0), galleryService.getGalleryById(1L));
    }

    @Test
    void createGallery() {
        when(galleryRepository.createGallery(new Gallery(1L,1L,"asfdg"))).thenReturn(galleryList.get(0));
        assertEquals(galleryList.get(0), galleryService.createGallery(new Gallery(1L,1L,"asfdg")));
    }

    @Test
    void updateGallery() {
        when(galleryRepository.updateGallery(new Gallery(1L,1L,"asfdg"))).thenReturn(galleryList.get(1));
        assertEquals(galleryList.get(1).getPhoto(), galleryService.updateGallery(new Gallery(1L,1L,"asfdg")).getPhoto());
    }

    @Test
    void deleteGalleryById() {
        galleryRepository.deleteGalleryById(1L);
        assertEquals(0, galleryService.getAllGalleries().size());
        verify(galleryRepository).deleteGalleryById(1L);
    }
}