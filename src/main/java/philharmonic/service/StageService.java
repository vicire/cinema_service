package philharmonic.service;

import java.util.List;
import philharmonic.model.Stage;

public interface StageService {
    Stage add(Stage stage);

    Stage get(Long id);

    List<Stage> getAll();
}
