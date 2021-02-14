package cinema.model.dto.response;

public class MovieSessionResponseDto {
    private Long movieSessionId;
    private String movieTitle;
    private String cinemaHallDescription;
    private String movieSessionTime;

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getCinemaHallDescription() {
        return cinemaHallDescription;
    }

    public void setCinemaHallDescription(String cinemaHallDescription) {
        this.cinemaHallDescription = cinemaHallDescription;
    }

    public String getMovieSessionTime() {
        return movieSessionTime;
    }

    public void setMovieSessionTime(String movieSessionTime) {
        this.movieSessionTime = movieSessionTime;
    }
}
