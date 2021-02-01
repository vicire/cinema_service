package cinema.dao;

import cinema.exception.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.MovieSession;
import cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> availableSessions = session.createQuery("SELECT ms "
                    + "from MovieSession as ms WHERE ms.movie.id = :movie_id "
                    + "AND date_format(ms.showTime, '%Y-%m-%d') = :date");
            availableSessions.setParameter("movie_id", movieId);
            availableSessions.setParameter("date", date.format(DateTimeFormatter.ISO_LOCAL_DATE));
            return availableSessions.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find all available movie sessions", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can`t add movie session " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
