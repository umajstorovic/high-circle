package com.movie.highcirclemaster.service;


import com.movie.highcirclemaster.conversion.MovieMapper;
import com.movie.highcirclemaster.domain.Movie;
import com.movie.highcirclemaster.dto.MovieDetailsDTO;
import com.movie.highcirclemaster.dto.MovieSummaryDTO;
import com.movie.highcirclemaster.exception.ResourceNotFoundException;
import com.movie.highcirclemaster.repository.MovieRepository;
import com.movie.highcirclemaster.specification.MovieFilter;
import com.movie.highcirclemaster.specification.MovieSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public List<MovieSummaryDTO> getPopularMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size).withSort(Sort.by("rating").descending());
        Page<Movie> moviePage = movieRepository.findAll(pageable);

        return moviePage.getContent()
                .stream()
                .map(movieMapper::toMovieSummaryDTO)
                .collect(Collectors.toList());
    }

    public List<MovieSummaryDTO> searchMovies(String query, int page, int size, String sortBy, List<? extends MovieFilter> movieFilters) {
        String sortField = Optional.ofNullable(sortBy).orElse("title");
        PageRequest pageable = PageRequest.of(page - 1, size, Sort.by(sortField).ascending());
        Specification<Movie> movieSpecs = buildMovieSpecification(query, movieFilters);
        Page<Movie> moviePage = movieRepository.findAll(movieSpecs, pageable);

        return moviePage.getContent()
                .stream()
                .map(movieMapper::toMovieSummaryDTO)
                .collect(Collectors.toList());
    }

    public MovieDetailsDTO getMovieDetails(Long id) {
        return movieRepository
                .findById(id)
                .map(movieMapper::toMovieDetailsDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

    }

    private Specification<Movie> buildMovieSpecification(String query, List<? extends MovieFilter> filters) {
        Specification<Movie> titleSpecification = Optional.ofNullable(query).map(MovieSpecification::titleContains).orElse(Specification.where(null));
        Specification<Movie> filterSpecification =  filters.stream()
                .map(MovieFilter::getSpecification)
                .reduce(Specification::and)
                .orElse(null);
        return titleSpecification.and(filterSpecification);
    }

}