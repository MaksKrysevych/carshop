package carshop.repository;

import carshop.model.entity.Storage;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class StorageRepository {
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public List<Storage> getAllStorages() {
        List<Storage> storages;
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            storages = session.createQuery("from Storage ", Storage.class).getResultList();

            session.getTransaction().commit();
        }
        return storages;
    }

    public Storage getStorageById(Long id) {
        try (final Session session = factory.openSession()) {

            Storage result = session.get(Storage.class, id);

            return result != null ? result : new Storage();
        }
    }

    public Storage createStorage(Storage storage) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            session.persist(storage);

            session.getTransaction().commit();
        }
        return storage;
    }

    public Storage updateStorage(Storage storage) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.merge(storage);

            session.getTransaction().commit();
        }
        return storage;
    }

    public void deleteStorageById(Long id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.remove(getStorageById(id));

            session.getTransaction().commit();
        }
    }
}
