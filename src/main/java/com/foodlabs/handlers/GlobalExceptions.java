package com.foodlabs.handlers;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.Setter;
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
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptions {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object>  handle(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object>  handle(NoResourceFoundException exception){
        final var error = customError(exception.getStatusCode().value(), exception.getLocalizedMessage());
        return ResponseEntity.status(exception.getStatusCode().value()).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object>  handle(HttpMessageNotReadableException exception){

        final var error = customError(400, "Required request body is missing");

        return ResponseEntity.status(error.getStatusCode()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage;
            if (Objects.equals(error.getCode(), "NotNull")) {
                errorMessage = "Field '" + fieldName + "' cannot be null.";
            } else if (Objects.equals(error.getCode(), "NotEmpty")) {
                errorMessage = "Field '" + fieldName + "' cannot be empty.";
            } else {
                errorMessage = error.getDefaultMessage();
            }
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handle(IllegalArgumentException exception){
        final var error = customError(400, exception.getLocalizedMessage());

        return ResponseEntity.status(error.getStatusCode()).body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object>  handle(HttpRequestMethodNotSupportedException exception){
        final var error = customError(exception.getStatusCode().value(), exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    private error customError(int status, String message){
        final var error = new error();

        error.setStatusCode(status);
        error.setMessage(message);

        return error;
    }

    @Getter
    @Setter
    static class error{
        private int statusCode;
        private String message;
    }

}
