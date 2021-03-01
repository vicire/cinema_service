package philharmonic.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import philharmonic.dao.ConcertDao;
import philharmonic.model.Concert;
import philharmonic.service.ConcertService;

@Service
public class ConcertServiceImpl implements ConcertService {
    private final ConcertDao concertDao;

    public ConcertServiceImpl(ConcertDao concertDao) {
        this.concertDao = concertDao;
    }

    @Override
    public Concert add(Concert concert) {
        return concertDao.add(concert);
    }

    @Override
    public Concert get(Long id) {
        return concertDao.get(id).orElseThrow(() ->
                new NoSuchElementException("Can`t find concert by id " + id));
    }

    @Override
    public List<Concert> getAll() {
        return concertDao.getAll();
    }
}
