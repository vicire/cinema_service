package philharmonic.service;

import java.util.List;
import philharmonic.model.Concert;

public interface ConcertService {
    Concert add(Concert concert);

    Concert get(Long id);

    List<Concert> getAll();
}
