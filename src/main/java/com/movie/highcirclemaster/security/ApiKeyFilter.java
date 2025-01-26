package com.movie.highcirclemaster.security;

import com.movie.highcirclemaster.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${app.api.key}")
    private String validApiKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String apiKey = request.getHeader("api_key");

        if (validApiKey.equals(apiKey)) {
            filterChain.doFilter(request, response);
        } else {
            throw new UnauthorizedException("Invalid or missing API key.");
        }
    }
}
