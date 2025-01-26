package com.movie.highcirclemaster.repository;

import com.movie.highcirclemaster.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

}
