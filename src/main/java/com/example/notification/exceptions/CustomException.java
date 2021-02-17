package com.example.notification.exceptions;

import com.example.notification.models.response.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@RestControllerAdvice
public class CustomException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        Error error = new Error(new Date(),"NOT_FOUND",exception.getMessage(),request.getDescription(false));
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<?> handleInvalidRequestException(InvalidRequestException exception, WebRequest request){
        Error error = new Error(new Date(),"INVALID_REQUEST",exception.getMessage(),request.getDescription(false));
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
