package carshop.repository;

import carshop.model.entity.User;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserRepository {
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public List<User> getAllUsers() {
        List<User> users;
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            users = session.createQuery("from User ", User.class).getResultList();

            session.getTransaction().commit();
        }
        return users;
    }

    public List<User> getAllUsersByPage(int pageNo, int usersPerPage) {
        List<User> users;
        int startUser = pageNo * usersPerPage - usersPerPage;

        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            users = session.createQuery("from User ", User.class)
                    .setFirstResult(startUser)
                    .setMaxResults(usersPerPage)
                    .getResultList();

            session.getTransaction().commit();
        }
        return users;
    }

    public User getUserByEmail(String email) {
        try (final Session session = factory.openSession()) {

            User result = session.get(User.class, email);

            return result != null ? result : new User();
        }
    }

    public User createUser(User user) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            session.merge(user);

            session.getTransaction().commit();
        }
        return user;
    }

    public User updateUser(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.merge(user);

            session.getTransaction().commit();
        }
        return user;
    }

    public void deleteUserByEmail(String email) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.remove(getUserByEmail(email));

            session.getTransaction().commit();
        }
    }
}
