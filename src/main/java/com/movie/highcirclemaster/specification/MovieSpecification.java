package com.movie.highcirclemaster.specification;

import com.movie.highcirclemaster.domain.Movie;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MovieSpecification {

    public static Specification<Movie> titleContains(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Movie> releaseDateAfter(String releaseDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //formatter = formatter.withLocale( putAppropriateLocaleHere );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate date = LocalDate.parse(releaseDate, formatter);
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("releaseDate"), date);
    }

    public static Specification<Movie> ratingGreaterThanOrEqual(String rating) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating);
    }

    public static Specification<Movie> popularityGreaterThanOrEqual(String popularity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("popularity"), popularity);
    }

    public static Specification<Movie> runtimeLessThanOrEqual(String runtime) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("runtime"), runtime);
    }

    public static Specification<Movie> languageEquals(String language) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("language"), language);
    }

    public static Specification<Movie> genreContains(String genre) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isMember(genre, root.get("genres"));
    }

    public static Specification<Movie> releaseDateBefore(String releaseDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        //formatter = formatter.withLocale( putAppropriateLocaleHere );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate date = LocalDate.parse(releaseDate, formatter);
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("releaseDate"), date);
    }
}
