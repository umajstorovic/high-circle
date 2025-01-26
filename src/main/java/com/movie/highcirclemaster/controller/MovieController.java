package com.movie.highcirclemaster.controller;

import com.movie.highcirclemaster.dto.MovieDetailsDTO;
import com.movie.highcirclemaster.dto.MovieSummaryDTO;
import com.movie.highcirclemaster.service.MovieService;
import com.movie.highcirclemaster.specification.MovieFilter;
import com.movie.highcirclemaster.specification.MovieFilters;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/popular")
    public List<MovieSummaryDTO> getPopularMovies(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return movieService.getPopularMovies(page, size);
    }

    @GetMapping("/search")
    public List<MovieSummaryDTO> searchMovies(
            @RequestParam String query,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) List<MovieFilters> filter
    ) {
        return movieService.searchMovies(query, page, size, sortBy, filter);
    }

    @GetMapping("/{id}")
    public MovieDetailsDTO getMovieDetails(@PathVariable Long id) {
        return movieService.getMovieDetails(id);
    }
}