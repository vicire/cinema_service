package philharmonic.dao.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import philharmonic.dao.ConcertSessionDao;
import philharmonic.exception.DataProcessingException;
import philharmonic.model.ConcertSession;

@Repository
public class ConcertSessionDaoImpl implements ConcertSessionDao {
    private final SessionFactory sessionFactory;

    public ConcertSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ConcertSession> findAvailableSessions(Long concertId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<ConcertSession> availableSessions = session.createQuery("SELECT cs "
                    + "from ConcertSession as cs WHERE cs.concert.id = :concert_id "
                    + "AND date_format(cs.showTime, '%Y-%m-%d') = :date");
            availableSessions.setParameter("concert_id", concertId);
            availableSessions.setParameter("date", date.format(DateTimeFormatter.ISO_LOCAL_DATE));
            return availableSessions.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find all available concert sessions by id "
                    + concertId + " on this date " + date, e);
        }
    }

    @Override
    public Optional<ConcertSession> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(ConcertSession.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can`t get concert session by id " + id, e);
        }
    }

    @Override
    public ConcertSession add(ConcertSession concertSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(concertSession);
            transaction.commit();
            return concertSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t add concert session " + concertSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(ConcertSession concertSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(concertSession);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t update concert session " + concertSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("delete from ConcertSession where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t remove concert session by id " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
