package philharmonic.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import philharmonic.dao.ConcertSessionDao;
import philharmonic.model.ConcertSession;
import philharmonic.service.ConcertSessionService;

@Service
public class ConcertSessionServiceImpl implements ConcertSessionService {
    private final ConcertSessionDao concertSessionDao;

    public ConcertSessionServiceImpl(ConcertSessionDao concertSessionDao) {
        this.concertSessionDao = concertSessionDao;
    }

    @Override
    public List<ConcertSession> findAvailableSessions(Long concertId, LocalDate date) {
        return concertSessionDao.findAvailableSessions(concertId, date);
    }

    @Override
    public ConcertSession add(ConcertSession concertSession) {
        return concertSessionDao.add(concertSession);
    }

    @Override
    public ConcertSession get(Long id) {
        return concertSessionDao.get(id).orElseThrow(() ->
                new NoSuchElementException("Can`t find concert session by id " + id));
    }

    @Override
    public void update(ConcertSession concertSession) {
        concertSessionDao.update(concertSession);
    }

    @Override
    public void delete(Long id) {
        concertSessionDao.delete(id);
    }
}
