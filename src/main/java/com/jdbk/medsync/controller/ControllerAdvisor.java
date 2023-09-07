package com.jdbk.medsync.controller;

import com.jdbk.medsync.exception.AlreadyBusySalleException;
import com.jdbk.medsync.exception.AlreadyExistException;
import com.jdbk.medsync.exception.NotFoundException;
import com.jdbk.medsync.exception.NotTheGoodPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyBusySalleException.class)
    public ResponseEntity<String> handle(AlreadyBusySalleException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handle(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<String> handle(AlreadyExistException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    @ExceptionHandler(NotTheGoodPasswordException.class)
    public ResponseEntity<String> handle(NotTheGoodPasswordException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
