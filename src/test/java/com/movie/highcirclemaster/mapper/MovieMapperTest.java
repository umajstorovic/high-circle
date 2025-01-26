package com.movie.highcirclemaster.mapper;

import com.movie.highcirclemaster.conversion.MovieMapper;
import com.movie.highcirclemaster.domain.Movie;
import com.movie.highcirclemaster.dto.MovieDetailsDTO;
import com.movie.highcirclemaster.dto.MovieSummaryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieMapperTest {

    private MovieMapper movieMapper;

    @BeforeEach
    void setUp() {
        movieMapper = new MovieMapper();
    }

    @Test
    void testToMovieDetailsDTO() {
        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setReleaseDate(LocalDate.of(2010, 7, 16));
        movie.setPosterUrl("https://example.com/inception.jpg");
        movie.setOverview("A mind-bending thriller about dreams within dreams.");
//        movie.setGenres(List.of("Action", "Sci-Fi")); // Uncomment if genres are implemented
        movie.setRating(8.8);
        movie.setRuntime(148);
        movie.setLanguage("English");

        MovieDetailsDTO dto = movieMapper.toMovieDetailsDTO(movie);

        assertNotNull(dto);
        assertEquals("Inception", dto.title());
        assertEquals("16-07-2010", dto.releaseDate());
        assertEquals("https://example.com/inception.jpg", dto.posterUrl());
        assertEquals("A mind-bending thriller about dreams within dreams.", dto.overview());
//        assertEquals(List.of("Action", "Sci-Fi"), dto.getGenres());
        assertEquals(8.8, dto.rating());
        assertEquals(148, dto.runtime());
        assertEquals("English", dto.language());
    }

    @Test
    void testToMovieSummaryDTO() {
        // Arrange
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Inception");
        movie.setReleaseDate(LocalDate.of(2010, 7, 16));
        movie.setPosterUrl("https://example.com/inception.jpg");
        movie.setRating(8.8);

        MovieSummaryDTO dto = movieMapper.toMovieSummaryDTO(movie);

        assertNotNull(dto);
        assertEquals(1L, dto.id());
        assertEquals("Inception", dto.title());
        assertEquals("16-07-2010", dto.releaseDate());
        assertEquals("https://example.com/inception.jpg", dto.posterUrl());
        assertEquals(8.8, dto.rating());
    }
}
