package com.movie.highcirclemaster.integration;

import com.movie.highcirclemaster.dto.MovieDetailsDTO;
import com.movie.highcirclemaster.dto.MovieSummaryDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO there are some failures regarding HttpMessageConverter
 * TODO also, flyway is not added
 */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Profile("integration")
class MovieIntegrationTest implements ContainersTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    static void setUp() {
        System.setProperty("DB_URL", postgresContainer.getJdbcUrl());
        System.setProperty("DB_USERNAME", postgresContainer.getUsername());
        System.setProperty("DB_PASSWORD", postgresContainer.getPassword());
    }

    @Test
    void testGetPopularMovies() {
        String url = "http://localhost:" + port + "/movies/popular?page=1&size=10";

        ResponseEntity<List<MovieSummaryDTO>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<MovieSummaryDTO>>() {
        });

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        List<MovieSummaryDTO> movies = response.getBody();
        assertNotNull(movies);
        assertTrue(movies.size() > 0);
    }

    @Test
    void testSearchMovies() {
        String url = "http://localhost:" + port + "/movies/search?query=Inception&page=1&size=10&sortBy=title";

        ResponseEntity<List<MovieSummaryDTO>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<MovieSummaryDTO>>() {
                });

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        List<MovieSummaryDTO> movies = response.getBody();
        assertNotNull(movies);
        assertTrue(movies.size() > 0);
        assertEquals("Inception", movies.get(0).title());
    }

    @Test
    void testGetMovieDetails() {
        Long movieId = 1L;
        String url = "http://localhost:" + port + "/movies/" + movieId;

        ResponseEntity<MovieDetailsDTO> response = restTemplate.getForEntity(url, MovieDetailsDTO.class);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        MovieDetailsDTO movieDetails = response.getBody();
        assertNotNull(movieDetails);
        assertEquals("Inception", movieDetails.title());
    }

    @Test
    void testGetMovieDetails_NotFound() {
        Long movieId = 9999L; // Non-existent ID
        String url = "http://localhost:" + port + "/movies/" + movieId;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Movie not found"));
    }
}

