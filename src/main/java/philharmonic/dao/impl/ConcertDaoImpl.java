package philharmonic.dao.impl;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import philharmonic.dao.ConcertDao;
import philharmonic.exception.DataProcessingException;
import philharmonic.model.Concert;

@Repository
public class ConcertDaoImpl implements ConcertDao {
    private final SessionFactory sessionFactory;

    public ConcertDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Concert add(Concert concert) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(concert);
            transaction.commit();
            return concert;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t insert Concert entity " + concert, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Concert> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Concert.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get concert by id " + id, e);
        }
    }

    @Override
    public List<Concert> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Concert> getAllConcertsQuery = session.createQuery("from Concert ",
                    Concert.class);
            return getAllConcertsQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get all concerts", e);
        }
    }
}
