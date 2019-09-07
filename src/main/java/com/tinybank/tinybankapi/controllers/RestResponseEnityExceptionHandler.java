package com.tinybank.tinybankapi.controllers;


import com.tinybank.tinybankapi.model.ErrorResponse;
import com.tinybank.tinybankapi.services.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;

@ControllerAdvice
@RestController
public class RestResponseEnityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(ResourceNotFoundException ex, WebRequest request){
        ErrorResponse errorResponse =
                new ErrorResponse( LocalDateTime.now(),"Not found", "404", "Student does not exits");
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
