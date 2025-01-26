package com.movie.highcirclemaster.service;

import com.movie.highcirclemaster.conversion.MovieMapper;
import com.movie.highcirclemaster.domain.Movie;
import com.movie.highcirclemaster.dto.MovieDetailsDTO;
import com.movie.highcirclemaster.dto.MovieSummaryDTO;
import com.movie.highcirclemaster.exception.ResourceNotFoundException;
import com.movie.highcirclemaster.repository.MovieRepository;
import com.movie.highcirclemaster.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPopularMovies() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Inception");
        Page<Movie> moviePage = new PageImpl<>(List.of(movie));
        when(movieRepository.findAll(any(Pageable.class))).thenReturn(moviePage);

        MovieSummaryDTO movieSummaryDTO = new MovieSummaryDTO(1L, "Inception", "16-07-2010", "https://example.com/inception.jpg", 8.8);
        when(movieMapper.toMovieSummaryDTO(movie)).thenReturn(movieSummaryDTO);

        List<MovieSummaryDTO> result = movieService.getPopularMovies(1, 10);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Inception", result.get(0).title());
        verify(movieRepository, times(1)).findAll(any(Pageable.class));
        verify(movieMapper, times(1)).toMovieSummaryDTO(movie);
    }

    @Test
    void testSearchMovies() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Inception");
        Page<Movie> moviePage = new PageImpl<>(List.of(movie));
        when(movieRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(moviePage);

        MovieSummaryDTO movieSummaryDTO = new MovieSummaryDTO(1L, "Inception", "16-07-2010", "https://example.com/inception.jpg", 8.8);
        when(movieMapper.toMovieSummaryDTO(movie)).thenReturn(movieSummaryDTO);

        List<MovieSummaryDTO> result = movieService.searchMovies("Inception", 1, 10, "title", Collections.emptyList());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Inception", result.get(0).title());
        verify(movieRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
        verify(movieMapper, times(1)).toMovieSummaryDTO(movie);
    }

    @Test
    void testGetMovieDetails_Success() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Inception");
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        MovieDetailsDTO movieDetailsDTO = new MovieDetailsDTO("Inception", "16-07-2010", "https://example.com/inception.jpg", "A mind-bending thriller about dreams within dreams.", 8.8, 148, "English");
        when(movieMapper.toMovieDetailsDTO(movie)).thenReturn(movieDetailsDTO);

        MovieDetailsDTO result = movieService.getMovieDetails(1L);

        assertNotNull(result);
        assertEquals("Inception", result.title());
        verify(movieRepository, times(1)).findById(1L);
        verify(movieMapper, times(1)).toMovieDetailsDTO(movie);
    }

    @Test
    void testGetMovieDetails_NotFound() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> movieService.getMovieDetails(1L));
        assertEquals("Movie not found with id: 1", exception.getMessage());
        verify(movieRepository, times(1)).findById(1L);
        verify(movieMapper, times(0)).toMovieDetailsDTO(any());
    }
}
