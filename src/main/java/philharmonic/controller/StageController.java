package philharmonic.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import philharmonic.model.Stage;
import philharmonic.model.dto.request.StageRequestDto;
import philharmonic.model.dto.response.StageResponseDto;
import philharmonic.service.StageService;
import philharmonic.service.mapper.StageMapper;

@RestController
@RequestMapping("/stages")
public class StageController {
    private final StageService stageService;
    private final StageMapper stageMapper;

    public StageController(StageService stageService,
                           StageMapper stageMapper) {
        this.stageService = stageService;
        this.stageMapper = stageMapper;
    }

    @PostMapping
    public void create(@RequestBody @Valid StageRequestDto cinemaHallDto) {
        Stage stage = stageMapper.toEntity(cinemaHallDto);
        stageService.add(stage);
    }

    @GetMapping
    public List<StageResponseDto> getAll() {
        return stageService.getAll().stream()
                .map(stageMapper::toDto)
                .collect(Collectors.toList());
    }
}
