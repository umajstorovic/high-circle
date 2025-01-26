package com.movie.highcirclemaster.conversion;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.movie.highcirclemaster.specification.MovieFilters;
import org.springframework.core.convert.converter.Converter;

public class MovieFilterConverter implements Converter<String, MovieFilters>

{
    @Override
    public MovieFilters convert(String source) {
        return MovieFilters.getFilter(source);
    }

}
