package com.movie.highcirclemaster.dto;

import java.time.LocalDate;
import java.util.List;

public record MovieDetailsDTO(
        String title,
        String releaseDate,
        String posterUrl,
        String overview,
//        List<String> genres,
        double rating,
        int runtime,
        String language,
        int popularity
) {}
