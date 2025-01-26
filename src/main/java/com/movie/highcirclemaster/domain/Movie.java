package com.movie.highcirclemaster.domain;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate releaseDate;

    private String posterUrl;

    @Column(length = 1000)
    private String overview;

    //@Type(ListArrayType.class)
//    @Column(
//            name = "genres",
//            columnDefinition = "text[]"
//    )
//    @ElementCollection
//    private List<String> genres;

    private double rating;

    private int runtime;

    private String language;
}
