package com.poolplatform.adapters.handlers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.poolplatform.domain.exceptions.RequestException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<?> userExitingException(RequestException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", ex.getMessage());

        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler()
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", ex.getCause());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<?> dataIntegrityViolationException(SQLException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", ex.getLocalizedMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
