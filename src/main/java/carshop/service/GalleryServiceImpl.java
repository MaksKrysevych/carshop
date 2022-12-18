package carshop.service;

import carshop.model.entity.Gallery;
import carshop.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;

    @Autowired
    public GalleryServiceImpl(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    @Override
    public List<Gallery> getAllGalleries() {
        return galleryRepository.getAllGalleries();
    }

    @Override
    public Gallery getGalleryById(Long id) {
        return galleryRepository.getGalleryById(id);
    }

    @Override
    public Gallery createGallery(Gallery gallery) {
        return galleryRepository.createGallery(gallery);
    }

    @Override
    public Gallery updateGallery(Gallery gallery) {
        return galleryRepository.updateGallery(gallery);
    }

    @Override
    public void deleteGalleryById(Long id) {
        galleryRepository.deleteGalleryById(id);
    }
}