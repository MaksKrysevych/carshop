package carshop.repository;

import carshop.model.entity.Advertisement;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public class AdvertisementRepository {
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public List<Advertisement> getAllAdverts() {
        List<Advertisement> advertisements;
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            advertisements = session.createQuery("from Advertisement ", Advertisement.class).getResultList();

            session.getTransaction().commit();
        }
        return advertisements;
    }

    public Advertisement getAdvertById(Long id) {
        try (final Session session = factory.openSession()) {

            Advertisement result = session.get(Advertisement.class, id);

            return result != null ? result : new Advertisement();
        }
    }

    public Advertisement createAdvert(Advertisement advertisement) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            session.persist(advertisement);

            session.getTransaction().commit();
        }
        return advertisement;
    }

    public Advertisement updateAdvert(Advertisement advertisement) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.merge(advertisement);

            session.getTransaction().commit();
        }
        return advertisement;
    }

    public void deleteAdvertById(Long id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.remove(getAdvertById(id));

            session.getTransaction().commit();
        }
    }
}
