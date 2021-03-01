package philharmonic.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import philharmonic.model.ConcertSession;

public interface ConcertSessionDao {
    List<ConcertSession> findAvailableSessions(Long movieId, LocalDate date);

    Optional<ConcertSession> get(Long id);

    ConcertSession add(ConcertSession concertSession);

    void update(ConcertSession concertSession);

    void delete(Long id);
}
