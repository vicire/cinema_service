package philharmonic.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import philharmonic.model.Concert;
import philharmonic.model.dto.request.ConcertRequestDto;
import philharmonic.model.dto.response.ConcertResponseDto;
import philharmonic.service.ConcertService;
import philharmonic.service.mapper.ConcertMapper;

@RestController
@RequestMapping("/concerts")
public class ConcertController {
    private final ConcertService concertService;
    private final ConcertMapper concertMapper;

    public ConcertController(ConcertService concertService, ConcertMapper concertMapper) {
        this.concertService = concertService;
        this.concertMapper = concertMapper;
    }

    @PostMapping
    public void create(@RequestBody @Valid ConcertRequestDto movieDto) {
        Concert concert = concertMapper.toEntity(movieDto);
        concertService.add(concert);
    }

    @GetMapping
    public List<ConcertResponseDto> getAll() {
        return concertService.getAll().stream()
                .map(concertMapper::toDto)
                .collect(Collectors.toList());
    }
}
