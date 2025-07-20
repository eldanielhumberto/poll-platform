package com.poolplatform.adapters.handlers;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.domain.exceptions.RequestException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<?> userExitingException(RequestException ex) {
        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Error " + ex.getStatus());
        responseDTO.setError(ex.getMessage());

        return new ResponseEntity<>(responseDTO, ex.getStatus());
    }

    @ExceptionHandler()
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Error " + HttpStatus.BAD_REQUEST);
        responseDTO.setError(ex.getAllErrors());

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<?> dataIntegrityViolationException(SQLException ex) {
        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Error " + HttpStatus.BAD_REQUEST);
        responseDTO.setError(ex.getLocalizedMessage());

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    public ResponseEntity<?> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Error " + HttpStatus.BAD_REQUEST);
        responseDTO.setError(ex.getLocalizedMessage());

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
