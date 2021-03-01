package philharmonic.service;

import java.time.LocalDate;
import java.util.List;
import philharmonic.model.ConcertSession;

public interface ConcertSessionService {
    List<ConcertSession> findAvailableSessions(Long movieId, LocalDate date);

    ConcertSession add(ConcertSession concertSession);

    ConcertSession get(Long id);

    void update(ConcertSession concertSession);

    void delete(Long id);
}
