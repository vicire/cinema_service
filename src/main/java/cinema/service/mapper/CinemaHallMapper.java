package cinema.service.mapper;

import cinema.model.CinemaHall;
import cinema.model.dto.request.CinemaHallRequestDto;
import cinema.model.dto.response.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper implements RequestDtoMapper<CinemaHall, CinemaHallRequestDto>,
        ResponseDtoMapper<CinemaHallResponseDto, CinemaHall> {
    @Override
    public CinemaHallResponseDto toDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setCinemaHallId(cinemaHall.getId());
        cinemaHallResponseDto.setCinemaHallCapacity(cinemaHall.getCapacity());
        cinemaHallResponseDto.setCinemaHallDescription(cinemaHall.getDescription());
        return cinemaHallResponseDto;
    }

    @Override
    public CinemaHall toEntity(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        return cinemaHall;
    }
}
