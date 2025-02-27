package com.movie.highcirclemaster.conversion;

import com.movie.highcirclemaster.domain.Movie;
import com.movie.highcirclemaster.dto.MovieDetailsDTO;
import com.movie.highcirclemaster.dto.MovieSummaryDTO;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class MovieMapper {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public MovieDetailsDTO toMovieDetailsDTO(Movie movie) {
        return new MovieDetailsDTO(
                movie.getTitle(),
                movie.getReleaseDate().format(dateFormatter),
                movie.getPosterUrl(),
                movie.getOverview(),
//                movie.getGenres(),
                movie.getRating(),
                movie.getRuntime(),
                movie.getLanguage(),
                movie.getPopularity()
        );
    }

    public MovieSummaryDTO toMovieSummaryDTO(Movie movie) {
        return new MovieSummaryDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getReleaseDate().format(dateFormatter),
                movie.getPosterUrl(),
                movie.getRating(),
                movie.getPopularity()
        );
    }
}
