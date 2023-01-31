package carshop.repository;

import carshop.model.entity.Request;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class RequestRepository {

    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public List<Request> getAllRequests() {
        List<Request> requests;
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            requests = session.createQuery("from Request ", Request.class).getResultList();

            session.getTransaction().commit();
        }
        return requests;
    }

    public List<Request> getRequestsByPage(int pageNo, int requestsPerPage) {
        List<Request> requests;
        int startRequest = pageNo * requestsPerPage - requestsPerPage;

        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            requests = session.createQuery("from Request ", Request.class)
                    .setFirstResult(startRequest)
                    .setMaxResults(requestsPerPage)
                    .getResultList();

            session.getTransaction().commit();
        }
        return requests;
    }

    public Request getRequestById(Long id) {
        try (final Session session = factory.openSession()) {

            Request result = session.get(Request.class, id);

            return result != null ? result : new Request();
        }
    }

    public Request createRequest(Request request) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            session.merge(request);

            session.getTransaction().commit();
        }
        return request;
    }

    public Request updateRequest(Request request) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.merge(request);

            session.getTransaction().commit();
        }
        return request;
    }

    public void deleteRequestById(Long id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.remove(getRequestById(id));

            session.getTransaction().commit();
        }
    }
}
