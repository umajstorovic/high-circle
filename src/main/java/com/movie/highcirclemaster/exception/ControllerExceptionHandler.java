package com.movie.highcirclemaster.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundExceptionViolation(ResourceNotFoundException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedExceptionViolation(UnauthorizedException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidFilterException.class)
    public ResponseEntity<String> handleInvalidFilterExceptionViolation(InvalidFilterException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
