package com.movie.highcirclemaster.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MovieSummaryDTO(
        Long id,
        String title,
        String releaseDate,
        String posterUrl,
        double rating,
        int popularity
) {}