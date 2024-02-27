package com.foodlabs.handlers;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptions {

    /**
     * Exception handler for handling DataIntegrityViolationException
     *
     * @param ex The DataIntegrityViolationException that was thrown.
     * @return ResponseEntity with the appropriate status and message.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handle(DataIntegrityViolationException ex) {
        String errorMessage = "Unique index or primary key violation";
        log.error(ex.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    /**
     * Exception handler for handling EntityNotFoundException.
     *
     * @return ResponseEntity with the appropriate status and message.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handle() {
        return ResponseEntity.notFound().build();
    }

    /**
     * Exception handler for handling NoResourceFoundException.
     *
     * @param exception The exception instance.
     * @return ResponseEntity containing the error information.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> handle(NoResourceFoundException exception) {
        final var error = customError(exception.getStatusCode().value(), exception.getLocalizedMessage());
        return ResponseEntity.status(exception.getStatusCode().value()).body(error.getMessage());
    }

    /**
     * Exception handler for handling HttpMessageNotReadableException.
     *
     * @param exception The exception instance.
     * @return ResponseEntity containing the error information.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handle(HttpMessageNotReadableException exception) {

        log.error(exception.getLocalizedMessage());
        final var error = customError(400, "Required request body is missing");

        return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
    }

    /**
     * Exception handler for handling MethodArgumentNotValidException.
     *
     * @param ex The exception instance.
     * @return ResponseEntity containing the error information.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            errors.put(fieldName, error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for handling IllegalArgumentException.
     *
     * @param exception The exception instance.
     * @return ResponseEntity containing the error information.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handle(IllegalArgumentException exception) {
        final var error = customError(400, exception.getLocalizedMessage());

        return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
    }

    /**
     * Exception handler for handling HttpRequestMethodNotSupportedException.
     *
     * @param exception The exception instance.
     * @return ResponseEntity containing the error information.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handle(HttpRequestMethodNotSupportedException exception) {
        final var error = customError(exception.getStatusCode().value(), exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error.getMessage());
    }

    /**
     * Creates a custom Error object with the specified status code and message.
     *
     * @param status  The HTTP status code of the error.
     * @param message The error message.
     * @return An Error object with the specified status code and message.
     */
    private Error customError(int status, String message) {
        final var error = new Error();

        error.setStatusCode(status);
        error.setMessage(message);

        return error;
    }

    /**
     * Error class to represent custom error information.
     */
    @Getter
    @Setter
    private static class Error {
        private int statusCode;
        private String message;
    }

}
