package carshop.repository;

import carshop.model.entity.Gallery;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class GalleryRepository {
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public List<Gallery> getAllGalleries() {
        List<Gallery> galleries;
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            galleries = session.createQuery("from Gallery ", Gallery.class).getResultList();

            session.getTransaction().commit();
        }
        return galleries;
    }

    public List<Gallery> getGalleriesByPage(int pageNo, int galleriesPerPage) {
        List<Gallery> galleries;
        int startGallery = pageNo * galleriesPerPage - galleriesPerPage;

        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            galleries = session.createQuery("from Gallery ", Gallery.class)
                    .setFirstResult(startGallery)
                    .setMaxResults(galleriesPerPage)
                    .getResultList();

            session.getTransaction().commit();
        }
        return galleries;
    }

    public Gallery getGalleryById(Long id) {
        try (final Session session = factory.openSession()) {

            Gallery result = session.get(Gallery.class, id);

            return result != null ? result : new Gallery();
        }
    }

    public Gallery createGallery(Gallery gallery) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            session.merge(gallery);

            session.getTransaction().commit();
        }
        return gallery;
    }

    public Gallery updateGallery(Gallery gallery) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.merge(gallery);

            session.getTransaction().commit();
        }
        return gallery;
    }

    public void deleteGalleryById(Long id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.remove(getGalleryById(id));

            session.getTransaction().commit();
        }
    }
}
