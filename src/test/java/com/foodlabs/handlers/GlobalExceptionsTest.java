package com.foodlabs.handlers;

import jakarta.persistence.EntityNotFoundException;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class GlobalExceptionsTest {

    @Test
    public void testDataIntegrityViolationExceptionHandler() {
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Unique index or primary key violation");

        GlobalExceptions handler = new GlobalExceptions();

        ResponseEntity<String> response = handler.handle(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Unique index or primary key violation", response.getBody());
    }

    @Test
    public void testEntityNotFoundExceptionHandler() {
        EntityNotFoundException ex = new EntityNotFoundException();

        GlobalExceptions handler = new GlobalExceptions();

        ResponseEntity<Object> response = handler.handle();

        assertEquals(NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testHandleHttpMessageNotReadableException() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("Required request body is missing");

        GlobalExceptions handler = new GlobalExceptions();

        ResponseEntity<Object> response = handler.handle(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Required request body is missing", response.getBody());
    }

    @Test
    public void testHandleNoResourceFoundException() {
        NoResourceFoundException exception = new NoResourceFoundException(HttpMethod.GET, "");

        GlobalExceptions handler = new GlobalExceptions();

        ResponseEntity<Object> response = handler.handle(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No static resource .", response.getBody());
    }


    @Test
    public void testHandleMethodArgumentNotValidException() {
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);

        FieldError fieldError = new FieldError("objectName", "fieldName", "error message");
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError));
        when(exception.getBindingResult()).thenReturn(bindingResult);

        GlobalExceptions handler = new GlobalExceptions();

        ResponseEntity<Object> response = handler.handle(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("fieldName", "error message");
        assertEquals(expectedErrors, response.getBody());
    }

    @Test
    public void testHandleIllegalArgumentException() {
        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");

        GlobalExceptions handler = new GlobalExceptions();

        ResponseEntity<Object> response = handler.handle(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid argument", response.getBody());
    }

    @Test
    public void testHandleHttpRequestMethodNotSupportedException() {
        HttpRequestMethodNotSupportedException exception = new HttpRequestMethodNotSupportedException("GET");

        GlobalExceptions handler = new GlobalExceptions();

        ResponseEntity<Object> response = handler.handle(exception);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertEquals("Request method 'GET' is not supported", response.getBody());
    }


}