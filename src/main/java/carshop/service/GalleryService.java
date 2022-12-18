package carshop.service;

import carshop.model.entity.Gallery;

import java.util.List;

public interface GalleryService {
    List<Gallery> getAllGalleries();

    Gallery getGalleryById(Long id);

    Gallery createGallery(Gallery gallery);

    Gallery updateGallery(Gallery gallery);

    void deleteGalleryById(Long id);
}
