package philharmonic.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import philharmonic.model.ConcertSession;
import philharmonic.model.dto.request.ConcertSessionRequestDto;
import philharmonic.model.dto.response.ConcertSessionResponseDto;
import philharmonic.service.ConcertSessionService;
import philharmonic.service.mapper.ConcertSessionMapper;

@RestController
@RequestMapping("/concert-sessions")
public class ConcertSessionController {
    private final ConcertSessionService concertSessionService;
    private final ConcertSessionMapper concertSessionMapper;

    public ConcertSessionController(ConcertSessionService concertSessionService,
                                    ConcertSessionMapper concertSessionMapper) {
        this.concertSessionService = concertSessionService;
        this.concertSessionMapper = concertSessionMapper;
    }

    @PostMapping
    public void create(@RequestBody @Valid ConcertSessionRequestDto concertSessionRequestDto) {
        ConcertSession concertSession = concertSessionMapper.toEntity(concertSessionRequestDto);
        concertSessionService.add(concertSession);
    }

    @GetMapping("/available")
    public List<ConcertSessionResponseDto> getAll(@RequestParam Long concertId,
                                                  @RequestParam @DateTimeFormat(pattern = "dd.MM."
                                                        + "yyyy") LocalDate date) {
        List<ConcertSession> allAvailableSessions = concertSessionService
                .findAvailableSessions(concertId, date);
        return allAvailableSessions.stream()
                .map(concertSessionMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody @Valid ConcertSessionRequestDto concertSessionRequestDto) {
        ConcertSession concertSession = concertSessionMapper.toEntity(concertSessionRequestDto);
        concertSession.setId(id);
        concertSessionService.update(concertSession);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        concertSessionService.delete(id);
    }
}
