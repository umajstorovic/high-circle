package com.movie.highcirclemaster.specification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.movie.highcirclemaster.domain.Movie;
import com.movie.highcirclemaster.exception.InvalidFilterException;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public enum MovieFilters implements MovieFilter {

    TITLE("title", MovieSpecification::titleContains),
    RELEASE_DATE_GTE("releaseDateAfter", MovieSpecification::releaseDateAfter),
    RUNTIME_LT("runtime", MovieSpecification::runtimeLessThanOrEqual),
    RATING_GTE("rating", MovieSpecification::ratingGreaterThanOrEqual),
    GENRE("genre", MovieSpecification::genreContains),

    ;
    private final String filterName;
    private Function<String, Specification<Movie>> specificationSupplier;
    private List<String> values;

    MovieFilters(String filterName, Function<String, Specification<Movie>> specificationSupplier) {
        this.filterName = filterName;
        this.specificationSupplier = specificationSupplier;
    }

    @Override
    public String getFilterName() {
        return filterName;
    }

    @JsonCreator
    public static MovieFilters getFilter(String filter) {
        String[] filterSplit = filter.split(":");
        String filterName = filterSplit[0];
        for (MovieFilters f : MovieFilters.values()) {
            if (f.getFilterName().equals(filterName)) {
                f.values = List.of(filterSplit[1].split(","));
                return f;
            }
        }
        throw new InvalidFilterException("Invalid filter: " + filterName);
    }

    public Specification<Movie> getSpecification() {
        return values.stream()
                .map(specificationSupplier)
                .reduce(Specification::or)
                .orElse(null);
    }
}
