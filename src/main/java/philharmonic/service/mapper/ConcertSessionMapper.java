package philharmonic.service.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;
import philharmonic.model.ConcertSession;
import philharmonic.model.dto.request.ConcertSessionRequestDto;
import philharmonic.model.dto.response.ConcertSessionResponseDto;
import philharmonic.service.ConcertService;
import philharmonic.service.StageService;

@Component
public class ConcertSessionMapper implements
        RequestDtoMapper<ConcertSession, ConcertSessionRequestDto>,
        ResponseDtoMapper<ConcertSessionResponseDto, ConcertSession> {
    private final ConcertService concertService;
    private final StageService stageService;

    public ConcertSessionMapper(ConcertService concertService, StageService stageService) {
        this.concertService = concertService;
        this.stageService = stageService;
    }

    @Override
    public ConcertSessionResponseDto toDto(ConcertSession concertSession) {
        ConcertSessionResponseDto dto = new ConcertSessionResponseDto();
        dto.setConcertSessionId(concertSession.getId());
        dto.setConcertSessionTime(concertSession.getShowTime().toString());
        dto.setConcertTitle(concertSession.getConcert().getTitle());
        dto.setStageDescription(concertSession.getStage().getDescription());
        return dto;
    }

    @Override
    public ConcertSession toEntity(ConcertSessionRequestDto concertSessionRequestDto) {
        ConcertSession concertSession = new ConcertSession();
        concertSession.setConcert(concertService.get(concertSessionRequestDto.getConcertId()));
        concertSession.setStage(stageService.get(concertSessionRequestDto.getStageId()));
        concertSession.setShowTime(LocalDateTime.parse(concertSessionRequestDto.getConcertTime(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return concertSession;
    }
}
