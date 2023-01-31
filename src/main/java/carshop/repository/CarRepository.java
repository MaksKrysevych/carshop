package carshop.repository;

import carshop.model.entity.Car;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CarRepository {
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public List<Car> getAllCars() {
        List<Car> cars;
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            cars = session.createQuery("from Car ", Car.class).getResultList();

            session.getTransaction().commit();
        }
        return cars;
    }

    public List<Car> getCarsByPage(int pageNo, int carsPerPage) {
        List<Car> cars;
        int startCar = pageNo * carsPerPage - carsPerPage;

        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            cars = session.createQuery("from Car ", Car.class)
                    .setFirstResult(startCar)
                    .setMaxResults(carsPerPage)
                    .getResultList();

            session.getTransaction().commit();
        }
        return cars;
    }

    public Car getCarById(Long id) {
        try (final Session session = factory.openSession()) {

            Car result = session.get(Car.class, id);

            return result != null ? result : new Car();
        }
    }

    public Car createCar(Car car) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            session.merge(car);

            session.getTransaction().commit();
        }
        return car;
    }

    public Car updateCar(Car car) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.merge(car);

            session.getTransaction().commit();
        }
        return car;
    }

    public void deleteCarById(Long id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();

            session.remove(getCarById(id));

            session.getTransaction().commit();
        }
    }
}
