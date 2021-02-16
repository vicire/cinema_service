package cinema.service.mapper;

import cinema.model.Movie;
import cinema.model.dto.request.MovieRequestDto;
import cinema.model.dto.response.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper implements RequestDtoMapper<Movie, MovieRequestDto>,
        ResponseDtoMapper<MovieResponseDto, Movie> {
    @Override
    public MovieResponseDto toDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setMovieId(movie.getId());
        movieResponseDto.setMovieTitle(movie.getTitle());
        movieResponseDto.setMovieDescription(movie.getDescription());
        return movieResponseDto;
    }

    @Override
    public Movie toEntity(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }
}
