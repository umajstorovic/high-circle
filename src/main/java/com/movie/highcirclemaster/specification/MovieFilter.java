package com.movie.highcirclemaster.specification;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.movie.highcirclemaster.domain.Movie;
import org.springframework.data.jpa.domain.Specification;

@JsonDeserialize(as = MovieFilters.class)
public interface MovieFilter {

    String getFilterName();
    Specification<Movie> getSpecification();
}