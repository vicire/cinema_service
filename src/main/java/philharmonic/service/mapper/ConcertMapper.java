package philharmonic.service.mapper;

import org.springframework.stereotype.Component;
import philharmonic.model.Concert;
import philharmonic.model.dto.request.ConcertRequestDto;
import philharmonic.model.dto.response.ConcertResponseDto;

@Component
public class ConcertMapper implements RequestDtoMapper<Concert, ConcertRequestDto>,
        ResponseDtoMapper<ConcertResponseDto, Concert> {
    @Override
    public ConcertResponseDto toDto(Concert concert) {
        ConcertResponseDto concertResponseDto = new ConcertResponseDto();
        concertResponseDto.setConcertId(concert.getId());
        concertResponseDto.setConcertTitle(concert.getTitle());
        concertResponseDto.setConcertDescription(concert.getDescription());
        return concertResponseDto;
    }

    @Override
    public Concert toEntity(ConcertRequestDto concertRequestDto) {
        Concert concert = new Concert();
        concert.setTitle(concertRequestDto.getTitle());
        concert.setDescription(concertRequestDto.getDescription());
        return concert;
    }
}
