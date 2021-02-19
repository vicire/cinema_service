package cinema.model.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MovieSessionRequestDto {
    @NotNull
    private String movieTime;
    @NotNull
    @Min(1)
    private Long movieId;
    @NotNull
    @Min(1)
    private Long cinemaHallId;

    public String getMovieTime() {
        return movieTime;
    }

    public void setMovieTime(String movieTime) {
        this.movieTime = movieTime;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }
}
